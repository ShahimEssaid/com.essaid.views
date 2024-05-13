package trash;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.session.ViewsSessionInternal;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;

public class IsRequestHandlerFactory implements RequestHandlerFactory {


//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ViewsSessionInternal session) {
    if (!requestType.equals(RequestType.IS) ||
        method.isDefault()) {
      return null;
    }
    RequestHandler handler = new IsRequestHandler(featureName, method, requestType, false,
        session);

    return handler;
  }

//  @Override
  public RequestHandler getHandler(Class<?> viewType, Method invokedMethod, RequestType requestType,
      ViewsManagerInternal manager) {
    return null;
  }

  @Override
  public RequestHandler getHandler(Request request) {
    return null;
  }

  public static class IsRequestHandler extends AbstractRequestHandler {

    public IsRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ViewsSessionInternal session) {
      super(featureName, method, requestType, defaultValue, session);
    }

//    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object featureValue = viewHandler.getValue().getFeatureValue(featureName);
      if (featureValue == null) {
        return false;
      }
      return featureValue;
    }

    @Override
    public void handle(Request request) {

    }
  }
}
