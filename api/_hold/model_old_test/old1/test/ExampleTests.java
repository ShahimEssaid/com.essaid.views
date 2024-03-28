package com.essaid.model_old.old1.test;

import com.essaid.model_old.old1.ModelTypeLoader;
import com.essaid.model_old.old1.ModelTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTests extends AbstractTest {

  @Test
  void memServiceLoader() {
    List<ModelTypeLoader> modelTypeLoaders = ModelTypes.INSTANCE.getModelTypeLoaders();
    assertThat(modelTypeLoaders).hasSize(1);
    assertThat(
        modelTypeLoaders.stream().filter(modelTypeLoader -> modelTypeLoader.canLoad(null))).hasSize(
        1);
  }
}
