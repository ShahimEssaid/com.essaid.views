package com.essaid.model;

import com.essaid.model.internal.map.MapEntityManager;
import com.essaid.model.internal.map.ModelledProxyFactory;
import com.essaid.model.internal.map.impl.AcceptRequestHandlerFactory;
import com.essaid.model.internal.map.impl.GetRequestHandlerFactory;
import com.essaid.model.internal.map.impl.IsRequestHandlerFactory;
import com.essaid.model.internal.map.impl.SetRequestHandlerFactory;
import com.essaid.model.model.Car;
import com.essaid.model.model.TestVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTests {

    static Config config = new Config();
    static EntityManager entityManager;
    private Car car;


    @BeforeAll
    static void createModel() {
        config.addEntityInterface(Car.class, List.of());

        config.addModelFactory(new ModelledProxyFactory());

        config.addHandlerFactory(new GetRequestHandlerFactory());
        config.addHandlerFactory(new SetRequestHandlerFactory());
        config.addHandlerFactory(new IsRequestHandlerFactory());
        config.addHandlerFactory(new AcceptRequestHandlerFactory());
        entityManager = new MapEntityManager("1", config);
    }

    @Test
    @Order(1)
    void test() {
         car = entityManager.createEntity(Car.class, "1");
         car.setColor("yellow");
         assertThat(car.getColor()).isEqualTo("yellow");
         car.setColor(null);
         assertThat(car.getColor()).isNull();

         car.setColor("Red");

         car.accept(new TestVisitor());

         car.setOwned(true);
//         assertThat(car.)

        assertThat(car.getDefaultIntValue()).isEqualTo(0);
        assertThat(car.isTruck()).isEqualTo(false);




    }

}
