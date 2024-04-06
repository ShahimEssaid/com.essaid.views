package com.essaid.views.flex;

import static org.assertj.core.api.Assertions.assertThat;

import com.essaid.views.proxy.Configs;
import com.essaid.views.Session;
import com.essaid.views.ViewsManager;
import hold.MapTransformer;
import com.essaid.views.internal.ViewInternal;
import com.essaid.views.proxy.impl.DefaultMapTransformer;
import com.essaid.views.proxy.internal.ImplUtils;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.flex.testmodel.Old_Car;
import com.essaid.views.flex.testmodel.PhenoType;
import com.essaid.views.flex.testmodel.Primitives;
import com.essaid.views.flex.testmodel.Old_Tire;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MainTests {

  @Test
  void tireBrandNameTest() {
    ViewsManager manager = Configs.createDefaultModelManager();
    Session session = manager.createSession();
    PhenoType phenoType = session.createView(PhenoType.class, null);


    Old_Tire tire = session.createView(Old_Tire.class, null );
//    PhenoTypeCombinded phenoTypeCombinded = tire._as(PhenoTypeCombinded.class);

    assertThat(tire).isNotNull();

//    TireInternal tireInternal = tire._internal();

    // get
    assertThat(tire.getBrandName()).isNull();

    // get set value
    tire.setBrandName("Set");
    assertThat(tire.getBrandName()).isEqualTo("Set");
    assertThat(tire.getBrandName_default("OrDefault")).isEqualTo("Set");
    assertThat(tire.getBrandName_set("OrSet")).isEqualTo("Set");
    tire.setBrandName(null);
    assertThat(tire.getBrandName()).isNull();

    // get default value
    assertThat(tire.getBrandName_default("Default")).isEqualTo("Default");
    assertThat(tire.getBrandName()).isNull();

    // get or set value
    tire.getBrandName_set("OrSet");
    assertThat(tire.getBrandName()).isEqualTo("OrSet");
    tire.setBrandName(null);

    // get the value from the object handler
    ViewHandler handler = ImplUtils.getHandler(tire);
    Object brandName = handler.getState().getFeatureValue("brandName");
    assertThat(brandName).isNull();
    tire.setBrandName("brand");
    brandName = handler.getState().getFeatureValue("brandName");
    assertThat(brandName).isEqualTo("brand");
    tire.setBrandName(null);

    // set and get
    tire.setBrandName("old");
    assertThat(tire.setBrandName_get("new")).isEqualTo("old");
    assertThat(tire.getBrandName()).isEqualTo("new");
    tire.setBrandName(null);

    // set and chain
    assertThat(tire.setBrandName_chain("chaining").getBrandName()).isEqualTo("chaining");
    assertThat(
        tire.setBrandName_chain("new").setBrandName_chain("chaining").getBrandName()).isEqualTo(
        "chaining");


  }

  @Test
  void tireSpareTest() {
    ViewsManager manager = Configs.createDefaultModelManager();
    Session session = manager.createSession();
    Old_Tire tire = session.createView(Old_Tire.class, null );

    assertThat(tire.isSpare()).isFalse();
    tire.setSpare(true);
    assertThat(tire.isSpare()).isTrue();
    assertThat(tire.getSpare()).isTrue();
    tire.setSpare(false);
    assertThat(tire.isSpare()).isFalse();
  }


  @Test
  void primitives() {
    ViewsManager manager = Configs.createDefaultModelManager();
    Session session = manager.createSession();
    Primitives p = session.createView(Primitives.class, null);

    assertThat(p.getByte()).isEqualTo((byte) 0);
    assertThat(p.getByte_default((byte) 1)).isEqualTo((byte) 1);
    assertThat(p.getByte_set((byte) 2)).isEqualTo((byte) 2);
    assertThat(p.getByte()).isEqualTo((byte) 2);
  }

  @Test
  void careSpareTire() {
    ViewsManager manager = Configs.createDefaultModelManager();
    Session session = manager.createSession();
    Old_Car car = session.createView(Old_Car.class, null);
    ViewInternal viewInternal = car._internal();

    assertThat(car.getSpareTire()).isNull();
    Old_Tire spare = car.getSpareTire_create();
    assertThat(spare).isNotNull();
    assertThat(spare.setBrandName_get("spare")).isNull();
    assertThat(car.getSpareTire().getBrandName()).isEqualTo("spare");
    assertThat(car.getSpareTire_create()).isSameAs(spare);

    assertThat(car.getTires()).isNull();
    List<Old_Tire> tires = car.getTires_create();
    assertThat(car.getTires_create()).isSameAs(tires);

    Old_Tire addedTire = car.getTires_add();
    addedTire.setBrandName("First added tire");
    assertThat(tires.get(0).getBrandName()).isEqualTo("First added tire");

    MapTransformer maptransformer = new DefaultMapTransformer();
    Map<String, Object> map = maptransformer.toMap(car);

    System.out.println(map);
  }


}
