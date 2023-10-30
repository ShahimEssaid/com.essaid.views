package com.essaid.model;

import com.essaid.model.internal.map.MapEntityManager;
import com.essaid.model.internal.map.ProxyInstanceFactory;
import com.essaid.model.internal.map.impl.*;
import com.essaid.model.model.Car;
import com.essaid.model.model.Tire;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTests {

    static Config config = new Config();
    static EntityManager entityManager;
    private Car car1;


    @BeforeAll
    static void createModel() {
        config.addEntityInterface(Car.class, List.of());

        config.addInstanceFactory(new ProxyInstanceFactory());

        config.addHandlerFactory(new DefaultRequestHandlerFactory());
        config.addHandlerFactory(new GetRequestHandlerFactory());
        config.addHandlerFactory(new SetRequestHandlerFactory());
        config.addHandlerFactory(new IsRequestHandlerFactory());
        config.addHandlerFactory(new AcceptRequestHandlerFactory());
        config.addHandlerFactory(new ObjectRequestHandlerFactory());
        config.addHandlerFactory(new CGetRequestHandlerFactory());
        config.addHandlerFactory(new CSetRequestHandlerFactory());
        entityManager = new MapEntityManager(config);

        Assertions.setMaxStackTraceElementsDisplayed(200);
    }

    @Test
    @Order(1)
    void test() {
        car1 = entityManager.createEntity(Car.class, "1");
        System.out.println("String:" + car1);
        System.out.println("Hash:" + car1.hashCode());

        Car car2 = entityManager.createEntity(Car.class, "2");

        assertThat(car1).isEqualTo(car1);
        assertThat(car1).isNotEqualTo(car2);


        car1.set_id_("someCar");
        assertThat(car1.get_id_()).isEqualTo("someCar");
        assertThatThrownBy(() -> {
            car1.set_id_("another id");
        }).isInstanceOf(IllegalStateException.class);


        assertThat(car1.getDefaultByte()).isEqualTo((byte) 0);
        assertThat(car1.getDefaultShort()).isEqualTo((short) 0);
        assertThat(car1.getDefaultInt()).isEqualTo(0);
        assertThat(car1.getDefaultLong()).isEqualTo(0L);
        assertThat(car1.getDefaultFloat()).isEqualTo(0.0f);
        assertThat(car1.getDefaultDouble()).isEqualTo(0.0d);
        assertThat(car1.getDefaultChar()).isEqualTo((char) 0);
        assertThat(car1.getDefautlboolean()).isEqualTo(false);
        assertThat(car1.isDefautlboolean()).isEqualTo(false);


        car1.setColor("yellow");
        assertThat(car1.getColor()).isEqualTo("yellow");
        car1.setColor(null);
        assertThat(car1.getColor()).isNull();

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
        Car car = car1.csetSpareTire(spareTire2);
        assertThat(car).isNotNull();
        assertThat(car).isSameAs(car1);
        assertThat(car1.getSpareTire()).isSameAs(spareTire2);
        assertThat(car1.isHasSpare()).isTrue();


    }

}
