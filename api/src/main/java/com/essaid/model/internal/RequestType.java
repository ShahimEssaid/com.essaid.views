package com.essaid.model.internal;

import java.beans.Introspector;
import java.lang.reflect.Method;

/**
 * The request types that are implemented in the library. The method suffixes are not
 * case-sensitive.
 */
public enum RequestType {
  /**
   * The usual JavaBean getter pattern. It returns the current value of the feature, or null if
   * there is no value. It returns the default value of a primitive even if such a feature does not
   * exist.
   * <br>
   * <br>
   * Examples:
   * <br> getFoo()
   */
  GET("get", ""),

  /**
   * The usual JavaBean is pattern for boolean. It returns the current value of the feature, or null
   * if there is no value. It returns the default value of a primitive even if such a feature does
   * not exist.
   * <br>
   * <br>
   * Examples:
   * <br> getFoo()
   */
  IS("is", ""),

  /**
   * A getter that accepts a default value to return if the feature does not exist. The default
   * value is not set. See {{@link #GET_OR_SET}} for that behavior.
   * <br>
   * <br>
   * Examples:
   * <br> getFoo_OrDefault(Object defaultReturnValue)
   */
  GET_OR_DEFAULT("get", "_default"),

  /**
   * A getter that accepts a value to set and return if there is no value. See
   * {@link #GET_OR_DEFAULT} for only providing a default value (as opposed to also setting it)
   * <br>
   * <br>
   * Examples:
   * <br> getFoo_OrSet(Object defaultSetAndReturnValue)
   */
  GET_OR_SET("get", "_set"),

  /**
   * A getter that returns the current value or creates it. Creating means instantiating the needed
   * object type, setting it, and returning it.  For primitives, this means setting the default
   * value of the primitive and returning it.
   * <br>
   * <br>
   * Examples:
   * <br> getFoo_OrCreate()
   */
  GET_OR_CREATE("get", "_create"),

  /**
   * The usual JavaBeans setter.
   * <br>
   * <br>
   * Examples:
   * <br> void setFoo(Object newValue)
   */
  SET("set", ""),

  /**
   * A setter that sets a new value, as usual, but also returns an existing value if it exists.
   * <br>
   * <br>
   * Examples:
   * <br> Object setFoo_get(Object newValue) // returns the old value
   */
  SET_AND_GET("set", "_get"),

  /**
   * A setter that sets a new value, as usual, but returns this for method chaining.
   * <br>
   * <br>
   * Examples:
   * <br> Object setFoo_chain(Object newValue) // returns this
   */
  SET_AND_CHAIN("set", "_chain"),

  GET_ADD("get", "_add"),

  _GET_VIEW_HANDLER("_getviewhandler", ""),

  /**
   * An unknown (i.e. not builtin) request type.
   */
  UNKNOWN("", "");


  private final String prefix;
  private final String suffix;

  RequestType(String prefix, String suffix) {
    this.prefix = prefix;
    this.suffix = suffix;
  }

  public static RequestType getRequestType(Method method) {

    RequestType type = UNKNOWN;
    final String methodName = method.getName().toLowerCase();

    if (methodName.startsWith(IS.prefix)) {
      if (method.getParameterCount() != 0 ||
          method.getReturnType() != boolean.class) {
        throw new IllegalStateException("Method signature does not match with the IS "
            + "pattern. Method: " + method);
      }
      type = IS;

    } else if (methodName.startsWith("get")) {
      if (methodName.endsWith(GET_OR_DEFAULT.suffix)) {
        if (method.getParameterCount() != 1 ||
            method.getReturnType() == Void.class ||
            method.getReturnType() != method.getParameterTypes()[0]) {
          throw new IllegalStateException("Method signature does not match with the GET OR DEFAULT "
              + "pattern. Method: " + method);
        }
        type = GET_OR_DEFAULT;
      } else if (methodName.endsWith(GET_OR_SET.suffix)) {
        if (method.getParameterCount() != 1 ||
            method.getReturnType() == Void.class ||
            method.getReturnType() != method.getParameterTypes()[0]) {
          throw new IllegalStateException("Method signature does not match with a GET OR SET "
              + "pattern. Method: " + method);
        }
        type = GET_OR_SET;
      } else if (methodName.endsWith(GET_OR_CREATE.suffix)) {
        if (method.getParameterCount() != 0 ||
            method.getReturnType() == Void.class) {
          throw new IllegalStateException("Method signature does not match with a GET OR CREATE "
              + "pattern. Method: " + method);
        }
        type = GET_OR_CREATE;
      } else if (methodName.endsWith(GET_ADD.suffix)) {
        if (method.getParameterCount() != 0 ||
            method.getReturnType() == Void.class) {
          throw new IllegalStateException("Method signature does not match with a GET ADD "
              + "pattern. Method: " + method);
        }
        type = GET_ADD;
      } else {
        if (method.getParameterCount() != 0 ||
            method.getReturnType() == Void.class) {
          throw new IllegalStateException("Method signature does not match with the GET "
              + "pattern. Method: " + method);
        }
        type = GET;
      }
    } else if (methodName.startsWith("set")) {

      if (methodName.endsWith(SET_AND_GET.suffix)) {
        if (method.getParameterCount() != 1 ||
            method.getReturnType() == Void.class ||
            method.getReturnType() != method.getParameterTypes()[0]) {
          throw new IllegalStateException("Method signature does not match with the SET AND GET "
              + "pattern. Method: " + method);
        }
        type = SET_AND_GET;
      } else if (methodName.endsWith(SET_AND_CHAIN.suffix)) {
        if (method.getParameterCount() != 1 ||
            method.getReturnType() == Void.class ||
            method.getReturnType() != method.getDeclaringClass()) {
          throw new IllegalStateException("Method signature does not match with the SET AND CHAIN "
              + "pattern. Method: " + method);
        }
        type = SET_AND_CHAIN;
      } else {
        if (method.getParameterCount() != 1 ||
            method.getReturnType() == Void.class) {
          throw new IllegalStateException("Method signature does not match with the SET "
              + "pattern. Method: " + method);
        }
        type = SET;
      }
    } else if (methodName.equals(_GET_VIEW_HANDLER.prefix)) {
      type = _GET_VIEW_HANDLER;
    }

    return type;
  }

  public String extractFeatureName(Method method) {

    String featureName = method.getName();
    String featureNameLowerCase = featureName.toLowerCase();
    if (!featureNameLowerCase.startsWith(prefix) || !featureNameLowerCase.endsWith(suffix)) {
      throw new IllegalArgumentException("Extracting feature name from method: " + method + " "
          + "with RequestType: " + this + " failed  due to type mismatch");
    }
    featureName = featureName.substring(prefix.length(), featureName.length() - suffix.length());
    return Introspector.decapitalize(featureName);
  }
}
