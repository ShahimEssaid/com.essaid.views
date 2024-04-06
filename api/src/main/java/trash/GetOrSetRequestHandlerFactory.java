package trash;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.internal.ViewInternal;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.internal.SessionInternal;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;


public class GetOrSetRequestHandlerFactory implements RequestHandlerFactory {


//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, SessionInternal session) {
    if (!requestType.equals(RequestType.GET_OR_SET) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrSetRequestHandler(featureName, method, requestType, session);

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


  public static class GetOrSetRequestHandler extends AbstractRequestHandler {

    public GetOrSetRequestHandler(String featureName, Method method,
        RequestType requestType, SessionInternal session) {
      super(featureName, method, requestType, null, session);
    }

//    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getState().getFeatureValue(featureName);
      if (value == null) {
        value = args[0];
        if (value != null) {
          viewHandler.getState().setFeatureValue(featureName, (ViewInternal) value);
        }
      }
      return value;
    }

    @Override
    public void handle(Request request) {

    }
  }
}
