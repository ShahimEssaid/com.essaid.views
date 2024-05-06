package com.essaid.views;

import com.essaid.views.proxy.Config;
import com.essaid.views.internal.ViewsManagerInternal;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface ViewsManager {


  ViewsSession createSession();

  Config getConfig();

  default ViewsManagerInternal internal() {
    return (ViewsManagerInternal) this;
  }

}
