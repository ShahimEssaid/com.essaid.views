package trash;

import com.essaid.views.ViewsManager;
import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultRequestHandlerFactory implements RequestHandlerFactory, RequestHandler {

  //  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ViewsManager viewsManager) {
    if (method.isDefault()) {
      return this;
    }
    return null;
  }

  //  @Override
  public Object handle(Object proxy, Method method, Object[] args,
      ViewHandler objectHandler) {

    try {
      return InvocationHandler.invokeDefault(proxy, method, args);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  //  @Override
  public RequestHandler getHandler(Class<?> viewType, Method invokedMethod, RequestType requestType,
      ViewsManagerInternal manager) {
    return null;
  }

  @Override
  public void handle(Request request) {

  }

  @Override
  public RequestHandler getHandler(Request request) {
    return null;
  }
}
