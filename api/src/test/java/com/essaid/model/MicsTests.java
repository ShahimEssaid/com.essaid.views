package com.essaid.model;

import com.essaid.model.internal.impl.ModelManagerImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MicsTests {

    private static ModelManagerImpl<Object, Object> modelManager;
    private Model<Object, Object> model;

    @BeforeAll
    static void createModelManager(){
        modelManager = new ModelManagerImpl<Object, Object>(Object.class, Object.class);
    }

    @Test
    @Order(0)
    void test(){

    }

    @Test
    @Order(10)
    void notNullManager(){
        assertThat(modelManager).isNotNull();
    }

    @Test
    @Order(20)
    void createModel(){
        this.model = modelManager.createModel(null);
        assertThat(model).isNotNull();
    }

}
