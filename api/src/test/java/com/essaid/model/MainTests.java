package com.essaid.model;

import com.essaid.model.internal.map.MapModel;
import com.essaid.model.internal.map.ModelledProxyFactory;
import com.essaid.model.internal.map.impl.GetRequestHandlerFactory;
import com.essaid.model.internal.map.impl.SetRequestHandlerFactory;
import com.essaid.model.model.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTests {

    static Config config = new Config();
    static Model model;
    private Car car;


    @BeforeAll
    static void createModel() {
        config.addEntityInterface(Car.class, List.of());
        config.addModelFactory(new ModelledProxyFactory());
        config.addHandlerFactory(new GetRequestHandlerFactory());
        config.addHandlerFactory(new SetRequestHandlerFactory());
        model = new MapModel("1", config);
    }

    @Test
    @Order(1)
    void test() {
         car = model.createEntity(Car.class, "1");
         car.setColor("yellow");
         assertThat(car.getColor()).isEqualTo("yellow");
         car.setColor(null);
         assertThat(car.getColor()).isNull();
    }

}
