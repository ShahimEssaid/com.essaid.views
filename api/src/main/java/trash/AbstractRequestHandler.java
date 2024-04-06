package trash;

import com.essaid.views.proxy.ModelUtils;
import com.essaid.views.View;
import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.internal.SessionInternal;
import com.essaid.views.internal.State;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import lombok.Getter;


public abstract class AbstractRequestHandler implements RequestHandler {

  protected final Method method;
  protected final String featureName;
  protected final RequestType requestType;
  protected final Object defaultValue;
  protected final SessionInternal session;


  public AbstractRequestHandler(String featureName, Method method, RequestType requestType,
      Object defaultValue, SessionInternal sessionInternal) {
    this.method = method;
    this.featureName = featureName;
    this.requestType = requestType;
    this.defaultValue = defaultValue;
    this.session = sessionInternal;
  }

//  protected static Object setFeature(String featureName, Object valueToSet,
//      State state) {
//
//    Object currentValue = null;
//
//    // unset if null
//    if (valueToSet == null) {
//      currentValue = state.unsetFeatureValue(featureName);
//    } else if (ModelUtils.isModelObject(valueToSet)) {
//      // set if already model object
////      currentValue = state.setFeatureValue(featureName, valueToSet);
//    } else {
//      View adaptedValue = state.getSession().adapt(valueToSet, View.class);
////      currentValue = state.setFeatureValue(featureName, adaptedValue);
//    }
//
//    return currentValue;
//  }

  protected static Object getFeature() {
    return null;
  }


  @Getter
  public static class GetterInfo {

    private final Method method;
    private final Class<?> returnType;
    private final boolean isList;
    private final Class<?> listInstanceType;
    private final boolean isMap;
    private final boolean isInstance;

    public GetterInfo(Method cgetterMethod) {
      String string = toString();
      this.method = cgetterMethod;
      this.returnType = method.getReturnType();
      if (List.class.isAssignableFrom(returnType)) {
        this.isList = true;
        this.isMap = false;
        this.isInstance = false;
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType pt) {
          Type listType = pt.getActualTypeArguments()[0];
          if (listType instanceof Class<?> cls) {
            listInstanceType = cls;

          } else {
            throw new IllegalStateException(
                "Method's list's generic type is not a simple Class:" + method);
          }
        } else {
          throw new IllegalStateException(
              "Method's List return type is not ParameterizedType:" + method);
        }
      } else if (Map.class.isAssignableFrom(returnType)) {
        this.isMap = true;
        this.isList = false;
        this.isInstance = false;
        listInstanceType = null;
      } else {
        this.isInstance = true;
        this.isList = false;
        this.isMap = false;
        listInstanceType = null;
      }


    }

  }

}
