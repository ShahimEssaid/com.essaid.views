package com.essaid.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.essaid.model.model.Car;
import com.essaid.model.model.Tire;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTests {

  static EntityManager entityManager;
  private Car car1;


  @BeforeAll
  static void createModel() {
    entityManager = Configs.createDefaultMapEntityManager();
    Assertions.setMaxStackTraceElementsDisplayed(200);
  }

  @Test
  @Order(1)
  void test() {
    car1 = entityManager.create(Car.class);
    System.out.println("String:" + car1);
    System.out.println("Hash:" + car1.hashCode());

    Car car2 = entityManager.create(Car.class);

    assertThat(car1).isEqualTo(car1);
    assertThat(car1).isNotEqualTo(car2);

    car1.set_id_("someCar");
    assertThat(car1.get_id_()).isEqualTo("someCar");
    assertThatThrownBy(() -> {
      car1.set_id_("another id");
    }).isInstanceOf(IllegalStateException.class);

    assertThat(car1.getByte()).isEqualTo((byte) 0);
    assertThat(car1.getShort()).isEqualTo((short) 0);
    assertThat(car1.getInt()).isEqualTo(0);
    assertThat(car1.getLong()).isEqualTo(0L);
    assertThat(car1.getFloat()).isEqualTo(0.0f);
    assertThat(car1.getDouble()).isEqualTo(0.0d);
    assertThat(car1.getChar()).isEqualTo((char) 0);
    assertThat(car1.getboolean()).isEqualTo(false);
    assertThat(car1.isboolean()).isEqualTo(false);

    car1.setColor("yellow");
    assertThat(car1.getColor()).isEqualTo("yellow");
    car1.setColor(null);
    assertThat(car1.getColor()).isNull();


    car1.setColor(null);
    assertThat(car1.getOrDefaultColor("yellow")).isEqualTo("yellow");
    assertThat(car1.getColor()).isNull();
    car1.setColor("red");
    assertThat(car1.getOrDefaultColor("yellow")).isEqualTo("red");
    car1.setColor(null);


    car1.setColor(null);
    assertThat(car1.getOrSetColor("yellow")).isEqualTo("yellow");
    assertThat(car1.getColor()).isEqualTo("yellow");
    car1.setColor("red");
    assertThat(car1.getOrSetColor("yellow")).isEqualTo("red");
    car1.setColor(null);



    car1.setOwned(true);
    assertThat(car1.isOwned()).isTrue();
    car1.setOwned(false);
    assertThat(car1.isOwned()).isFalse();

    assertThat(car1.getSpareTire()).isNull();
    assertThat(car1.cgetSpareTire()).isNotNull();
    assertThat(car1.getSpareTire()).isNotNull();
    assertThat(car1.cgetSpareTire()).isInstanceOf(Tire.class);

    Tire spareTire1 = car1.getSpareTire();
    assertThat(car1.cgetSpareTire()).isSameAs(spareTire1);

    Tire spareTire2 = entityManager.create(Tire.class);
    spareTire2.setName("2");
    Car car = car1.csetSpareTire(spareTire2);
    assertThat(car).isNotNull();
    assertThat(car).isSameAs(car1);
    assertThat(car1.getSpareTire()).isSameAs(spareTire2);
    assertThat(car1.isHasSpare()).isTrue();
    assertThat(car1.getSpareTire().getName()).isEqualTo("2");

    assertThat(car1.getTireLoadList()).isNull();
    List<Tire> tires = car1.cgetTireLoadList();
    assertThat(tires).isNotNull();
    Tire tire = car1.addTireLoadList();
    assertThat(tire).isNotNull();
    assertThat(car1.cgetTireLoadList()).isSameAs(tires);
    assertThat(car1.getTireLoadList()).hasSize(1);


  }

}
