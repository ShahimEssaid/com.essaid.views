package trash;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;

public class GetOrDefaultRequestHandlerFactory implements RequestHandlerFactory {


//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ViewsSessionInternal session) {

    if (!requestType.equals(RequestType.GET_OR_DEFAULT) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrDefaultRequestHandler(featureName, method, requestType, session);

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

  public static class GetOrDefaultRequestHandler extends AbstractRequestHandler {

    public GetOrDefaultRequestHandler(String featureName, Method method, RequestType requestType,
        ViewsSessionInternal session) {
      super(featureName, method, requestType, null, session);
    }

//    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getValue().getFeatureValue(featureName);
      return value != null ? value : args[0];
    }

    @Override
    public void handle(Request request) {

    }
  }
}
