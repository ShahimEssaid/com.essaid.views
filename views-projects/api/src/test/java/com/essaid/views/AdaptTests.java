package com.essaid.views;

import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.proxy.Configs;
import com.essaid.views.proxy.internal.StateKeys;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AdaptTests {

  static ViewsManager manager = Configs.createDefaultModelManager2();
  static ViewsSessionInternal session = (ViewsSessionInternal) manager.createSession();

  @Test
  void adapt_byte(){
    View adapted = (View) session.adapt((byte) 1, View.class);
    Object value = adapted._getViewHandler().getValue().getValue(StateKeys.EXTERNAL_VALUE);
    assertThat(value).isInstanceOf(Byte.class);
    Byte aByte = session.adapt(value, Byte.class);
    assertThat(aByte).isEqualTo((byte) 1);
    String aString = session.adapt(adapted, String.class);
    assertThat(aString).isEqualTo("1");
  }



}
