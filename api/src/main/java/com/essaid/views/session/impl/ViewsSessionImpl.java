package com.essaid.views.session.impl;

import com.essaid.views.View;
import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.value.Value;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.impl.ViewHandlerImpl;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.StateKeys;
import java.lang.constant.Constable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class ViewsSessionImpl extends AbstractViewsSession {


  private final ConcurrentHashMap<Method, RequestHandler> requestHandlers = new ConcurrentHashMap<>();

  public ViewsSessionImpl(ViewsManagerInternal flexModel) {
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
  public <T> T createView(Class<T> viewType, Value state, Class<?>... customDefaults) {
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
    ViewHandlerImpl viewHandler = new ViewHandlerImpl(viewType, allInterfaces, state);
    return (T) Proxy.newProxyInstance(viewType.getClassLoader(), allInterfaces, viewHandler);
  }


//  @Override
  public <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults) {
    if (object == null) {
      return null;
    }

    Class<?> objectType = object.getClass();
    if (viewType.isAssignableFrom(objectType)) {
      return (T) object;
    }

    if (viewType.isPrimitive()) {
      throw new IllegalArgumentException("Can't adapt to primitives type: " + viewType);
    }

    // is this a model object?
    Object externalValue = null;
    Value value = null;

    if (object instanceof View) {
      value = ((View) object).__viewHandler().getValue();
    }

    if (value != null) {
      if (value.getSession() != this) {
        throw new IllegalArgumentException(
            "Can't adapt an object that belongs to a different session. Object: " + object);
      }
      externalValue = value.getValue(StateKeys.EXTERNAL_VALUE);

      // View to View adaptation while we're here, create new proxy with same ViewHandler
      // We can do this here because we know the target is a View.
      if (View.class.isAssignableFrom(viewType)) {
        Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);
        return createView(viewType, value, allInterfaces);
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
      if (value != null &&
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
    View view = (View) createView(viewType, null, customDefaults);
    view.__viewHandler().getValue().setValue(StateKeys.EXTERNAL_VALUE, object);
    return (T) view;

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
  public void process(Request request) {
    getRequestHandler(request).handle(request);
  }

  @Override
  public View setFeature(Value state, String featureName, Object value, Request request) {
    if (value == null) {
      return state.unsetFeatureValue(featureName);
    }

    View internal = null;
    if (value instanceof View) {
      internal = (View) value;
      if (internal.__viewHandler().getValue().getSession() != this) {
        throw new IllegalArgumentException("Can't set cross-sessions. Set request: " + request);
      }
    } else {
      internal = (View) adapt(value, View.class);
    }
    View oldInternal = state.setFeatureValue(featureName, internal);
    return oldInternal;
  }

  @Override
  public View getFeature(Value state, String featureName, Request request) {
    View featureValue = state.getFeatureValue(featureName);
    return featureValue;
  }




  protected RequestHandler getRequestHandler(Request request) {
    Method invokedMethod = request.getInvokedMethod();
    Class<? extends View> viewClass = request.getView().getClass();

    Method method = getManager().getClientMethod(request);

    return requestHandlers.computeIfAbsent(method,
        m -> findRequestHandler(request));
  }

  private RequestHandler findRequestHandler(Request request) {
    Optional<RequestHandler> first = getManager().getConfig().getHandlerFactories().stream()
        .map(f -> f.getHandler(request)).filter(Objects::nonNull).findFirst();

    if (!first.isPresent()) {
      throw new IllegalArgumentException("Can't find request handler for request: " + request);
    }
    return first.get();
  }
}
