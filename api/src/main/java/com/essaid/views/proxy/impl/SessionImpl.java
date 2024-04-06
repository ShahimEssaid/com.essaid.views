package com.essaid.views.proxy.impl;

import com.essaid.views.View;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.internal.State;
import com.essaid.views.proxy.internal.StateKeys;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.internal.ViewInternal;
import java.lang.constant.Constable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;


public class SessionImpl extends AbstractSession {


  public SessionImpl(ViewsManagerInternal flexModel) {
    super(flexModel);
  }

  public static String adaptToString(Object value) {
    return value.toString();
  }

  public static Integer adaptToInteger(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).intValue();
    }

    if (String.class == valueClass) {
      try {
        return Integer.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Long adaptToLong(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).longValue();
    }
    if (String.class == valueClass) {
      try {
        return Long.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Byte adaptToByte(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).byteValue();
    }
    if (String.class == valueClass) {
      try {
        return Byte.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Short adaptToShort(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).shortValue();
    }
    if (String.class == valueClass) {
      try {
        return Short.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Float adaptToFloat(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).floatValue();
    }
    if (String.class == valueClass) {
      try {
        return Float.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Double adaptToDouble(Object value) {
    Class<?> valueClass = value.getClass();
    if (Number.class.isAssignableFrom(valueClass)) {
      return ((Number) value).doubleValue();
    }
    if (String.class == valueClass) {
      try {
        return Double.valueOf((String) value);
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }
    return null;
  }

  public static Character adaptToCharacter(Object value) {
    Class<?> valueClass = value.getClass();
    if (Character.class == valueClass) {
      return (Character) value;
    }
    return null;
  }

  public static Boolean adaptToBoolean(Object value) {
    Class<?> valueClass = value.getClass();

    if (String.class == valueClass) {
      try {
        return !((String) value).isEmpty();
      } catch (NumberFormatException e) {
        // ignore and return null
      }
    }

    if (Number.class.isAssignableFrom(valueClass)) {
      Number number = (Number) value;

      // todo: do this based on double min checking, if needed
      return number.longValue() != 0;
    }

    return false;
  }

  @Override
  public <T> T createView(Class<T> viewType, State state, Class<?>... customDefaults) {
    Class<?> implementation = viewManager.getConfig().getImplementation(viewType);
    if (implementation != null) {
      try {
        return (T) implementation.getConstructor().newInstance();
      } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
               IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);
    if (state == null) {
      state = createState();
    }
    ViewHandler viewHandler = new ViewHandlerImpl(viewType,  allInterfaces, state);
    return (T) Proxy.newProxyInstance(viewType.getClassLoader(), allInterfaces, viewHandler);
  }

  @Override
  public <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults) {
    if (object == null) {
      return null;
    }

    Class<?> objectType = object.getClass();
    if (viewType.isAssignableFrom(objectType)) {
      return (T) object;
    }

    if (viewType.isPrimitive()) {
      if (Number.class == objectType) {
        if (Byte.TYPE == viewType && Byte.class == objectType) {
          return (T) object;
        }
        if (Short.TYPE == viewType && Short.class == objectType) {
          return (T) object;
        }
        if (Integer.TYPE == viewType && Integer.class == objectType) {
          return (T) object;
        }
        if (Long.TYPE == viewType && Long.class == objectType) {
          return (T) object;
        }
        if (Float.TYPE == viewType && Float.class == objectType) {
          return (T) object;
        }
        if (Double.TYPE == viewType && Double.class == objectType) {
          return (T) object;
        }
      }
      if (Boolean.TYPE == viewType && Boolean.class == objectType) {
        return (T) object;
      }
      if (Character.TYPE == viewType && Character.class == objectType) {
        return (T) object;
      }
    }

    // is this a model object?
    Object externalValue = null;
    State state = null;

    if(object instanceof ViewInternal){
      state = ((ViewInternal)object).__getState();
    }


    if (state != null) {
      externalValue = state.getValue(StateKeys.EXTERNAL_VALUE);
      if (state.getSession() != this) {
        throw new IllegalArgumentException(
            "Can't adapt an object that belongs to a different session. Object: " + object);
      }

      // View to View adaptation while we're here, create new proxy with same ViewHandler
      // We can do this here because we know the target is a View.
      if (View.class.isAssignableFrom(viewType)) {
        Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);
        return createView(viewType, state, allInterfaces);
      }
    }

    // try to use external value first, if we have one.
    if (externalValue != null) {
      T adaptExternalValue = adaptExternalValue(externalValue, viewType);
      if (adaptExternalValue != null) {
        return adaptExternalValue;
      }
    }

    if (viewType.isInterface()) {

      // see if we have an implementation that is meant to wrap the view handler
      if (state != null &&
          !Collection.class.isAssignableFrom(viewType) &&
          !Map.class.isAssignableFrom(viewType)) {
        Class<?> implementation = viewManager.getConfig().getImplementation(viewType);
        if (implementation != null) {
          try {
            Constructor<?> constructor = implementation.getConstructor(objectType);
            return (T) constructor.newInstance(object);
          } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                   InvocationTargetException e) {
            // ignore
          }
        }
      }


    }

    // wrap it as an external with a new state
    // we assume the interface is a view interface, and create a view
    ViewInternal viewInternal = (ViewInternal) createView(viewType, null, customDefaults);
    viewInternal.__getState().setValue(StateKeys.EXTERNAL_VALUE, object);
    return (T) viewInternal;

  }


  /**
   * Assumption is that the value is not already assignable, and it needs to be adapted.
   *
   * @param externalValue
   * @param viewType
   * @param <T>
   * @return
   */
  private <T> T adaptExternalValue(Object externalValue, Class<T> viewType) {
    if (viewType.isAssignableFrom(externalValue.getClass())) {
      return (T) externalValue;
    }

    Class<?> externalType = externalValue.getClass();

    if (Constable.class.isAssignableFrom(externalType)) {

      if (String.class == viewType) {
        return (T) adaptToString(externalValue);
      }

      if (viewType == Integer.TYPE || viewType == Integer.class) {
        return (T) adaptToInteger(externalValue);
      }

      if (viewType == Long.TYPE || viewType == Long.class) {
        return (T) adaptToLong(externalValue);
      }

      if (viewType == Byte.TYPE || viewType == Byte.class) {
        return (T) adaptToByte(externalValue);
      }

      if (viewType == Short.TYPE || viewType == Short.class) {
        return (T) adaptToShort(externalValue);
      }

      if (viewType == Float.TYPE || viewType == Float.class) {
        return (T) adaptToFloat(externalValue);
      }

      if (viewType == Double.TYPE || viewType == Double.class) {
        return (T) adaptToDouble(externalValue);
      }

      if (viewType == Character.TYPE || viewType == Character.class) {
        return (T) adaptToCharacter(externalValue);
      }

      if (viewType == Boolean.TYPE || viewType == Boolean.class) {
        return (T) adaptToBoolean(externalValue);
      }

      if (viewType == Void.TYPE || viewType == Void.class) {
        return (T) null;
      }
    }

    return null;
  }


  @Override
  public Method getClientMethod(Request request) {
    return viewManager.getClientMethod(request);
  }

  @Override
  public void process(Request request) {
    getRequestHandler(request).handle(request);
  }

  @Override
  public ViewInternal setFeature(State state, String featureName, Object value, Request request) {
    if(value == null){
      return state.unsetFeatureValue(featureName);
    }

    ViewInternal internal = null;
    if(value instanceof ViewInternal){
      internal = (ViewInternal) value;
      if(internal.__getState().getSession() != this){
        throw new IllegalArgumentException("Can't set cross-sessions. Set request: " + request);
      }
    } else {
      internal = (ViewInternal) adapt(value, View.class);
    }
    ViewInternal oldInternal = state.setFeatureValue(featureName, internal);
    return oldInternal;
  }

  @Override
  public ViewInternal getFeature(String featureName, Request request) {
    return null;
  }


}
