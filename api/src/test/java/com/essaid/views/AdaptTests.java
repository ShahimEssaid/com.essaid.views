package com.essaid.views;

import static org.assertj.core.api.Assertions.assertThat;

import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.internal.Factories;
import com.essaid.views.internal.impl.FactoriesImpl;
import com.essaid.views.proxy.Config;
import com.essaid.views.proxy.impl.ViewsManagerImpl;
import com.essaid.views.session.ViewsSessionInternal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdaptTests {

  static ViewsManager manager = Configs.createDefaultModelManager2();
  static ViewsSessionInternal session = (ViewsSessionInternal) manager.createSession();
  private static Logger logger = LoggerFactory.getLogger(AdaptTests.class);

  @BeforeAll
  static void setup() {
    logger.info("Doing setup");
    Config config = new Config();
    Configs.addDefaultAdapters(config);
    config.addService(Factories.class
    , new FactoriesImpl());
    manager = new ViewsManagerImpl(config);
    session = (ViewsSessionInternal) manager.createSession();
  }

  @Test
  void null_to_String_no_default() {
    String aDefault = session.adapt(null, String.class, String.class, null);
    assertThat(aDefault).isNull();
  }

  @Test
  void null_to_String_with_default() {
    String aDefault = session.adapt(null, String.class, String.class, null);
    assertThat(aDefault).isEqualTo("Default");
  }

  @Test
  void string_to_String_no_default() {
    String adaptee = "string";
    String string = session.adapt(adaptee, String.class, String.class, null);
    assertThat(string).isSameAs(adaptee);
  }


  @Test
  void string_to_View() {
    String adaptee = "string";
    AdaptRequest adaptRequest = session.getObjectFactory()
        .createAdaptRequest(adaptee, View.class, View.class, new Class[]{}, session, null);
    View view = session.adapt(adaptRequest);
    assertThat(view).isNotNull();

    assertThat(view.__viewHandler().getValue().getValue()).isEqualTo("string");
  }
//  @Test
//  void adapt_byte(){
//    View adapted = (View) session.adapt((byte) 1, View.class);
//    Object value = adapted._getViewHandler().getValue().getValue(StateKeys.EXTERNAL_VALUE);
//    assertThat(value).isInstanceOf(Byte.class);
//    Byte aByte = session.adapt(value, Byte.class);
//    assertThat(aByte).isEqualTo((byte) 1);
//    String aString = session.adapt(adapted, String.class);
//    assertThat(aString).isEqualTo("1");
//  }


}
