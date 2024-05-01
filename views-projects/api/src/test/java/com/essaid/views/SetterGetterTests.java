package com.essaid.views;

import static org.assertj.core.api.Assertions.assertThat;

import com.essaid.views.flex.testmodel.Primitives;
import com.essaid.views.flex.testmodel.Tire;
import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.internal.Value;
import com.essaid.views.proxy.Configs;
import org.junit.jupiter.api.Test;

public class SetterGetterTests {

  public static ViewsManager viewsManager = Configs.createDefaultModelManager2();
  public static ViewsSessionInternal session = (ViewsSessionInternal) viewsManager.createSession();


  @Test
  void tireName() {
    Tire tire = session.createView(Tire.class, null);
    tire.setName("Test");

    View view = (View) tire;
    Value value = view._getViewHandler().getValue();
    View tireValue = value.getFeatureValue("name");
    String adapt = session.adapt(tireValue, String.class );
    assertThat(adapt).isEqualTo("Test");

    assertThat(tire.getName()).isEqualTo("Test");
    assertThat(value.getFeatureNames()).size().isEqualTo(1);

    tire.setName(null);
    assertThat(tire.getName()).isNull();
    assertThat(value.getFeatureNames()).isEmpty();
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
