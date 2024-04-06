package com.essaid.views;

import static org.assertj.core.api.Assertions.assertThat;

import com.essaid.views.flex.testmodel.Primitives;
import com.essaid.views.flex.testmodel.Tire;
import com.essaid.views.internal.SessionInternal;
import com.essaid.views.internal.State;
import com.essaid.views.internal.ViewInternal;
import com.essaid.views.proxy.Configs;
import org.junit.jupiter.api.Test;

public class SetterGetterTests {

  public static ViewsManager viewsManager = Configs.createDefaultModelManager2();
  public static SessionInternal session = (SessionInternal) viewsManager.createSession();


  @Test
  void tireName() {
    Tire tire = session.createView(Tire.class, null);
    tire.setName("Test");

    ViewInternal viewInternal = (ViewInternal) tire;
    State state = viewInternal.__getState();
    ViewInternal tireValue = state.getFeatureValue("name");
    String adapt = session.adapt(tireValue, String.class );
    assertThat(adapt).isEqualTo("Test");

    assertThat(tire.getName()).isEqualTo("Test");
    assertThat(state.getFeatureNames()).size().isEqualTo(1);

    tire.setName(null);
    assertThat(tire.getName()).isNull();
    assertThat(state.getFeatureNames()).isEmpty();
  }


  @Test
  void primitives() {
    Primitives primitives = session.createView(Primitives.class, null);

    assertThat(primitives.getByte()).isEqualTo((byte) 0);

    primitives.setByte((byte) 1);
    assertThat(primitives.getByte()).isEqualTo((byte) 1);
    assertThat(primitives.getByte()).isNotEqualTo(1);
  }


}
