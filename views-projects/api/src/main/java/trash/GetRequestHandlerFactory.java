package trash;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.impl.handler.GetRequestHandler;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.proxy.internal.Request;

public class GetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(Request request) {
    RequestType requestType = RequestType.getRequestType(request);
    if (RequestType.GET != requestType ||
        request.getInvokedMethod().isDefault()) {
      return null;
    }
    return new GetRequestHandler(request);
  }
}
