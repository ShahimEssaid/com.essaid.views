package trash;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.impl.handler.SetRequestHandler;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import com.essaid.views.proxy.internal.Request;

public class SetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(Request request) {

    RequestType requestType = RequestType.getRequestType(request);

    if (!RequestType.SET.equals(requestType) ||
        request.getInvokedMethod().isDefault()) {
      return null;
    }

    return new SetRequestHandler(request);
  }


}
