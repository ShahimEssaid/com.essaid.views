package com.essaid.model_old.old1;

import com.essaid.model_old.old1.impl.ModelTypesImpl;

import java.util.List;

/**
 * A registry for available model types. A client can pass an object that represents an environment of their choice
 * and hopefully get back a {@link ModelType instance for that environment.}
 */
public interface ModelTypes {

    static ModelTypes INSTANCE = new ModelTypesImpl();

    /**
     * @param environmentHint is an object that hints at the native environment for which a {@link Model} instance
     *                        * will be created and used by the client. For example, if the environment is going to be related to FHIR R5, a
     *                        * FhirContext can be passed. If it is protobuf v3, the Message class can be passed.  Each ModelType is able to
     *                        * detect if it is applicable to a hint, first one wins.  Load the model types you need
     *                        to the classpath. If null is passed, a memory model type is returned.
     * @return the model type based on the hint
     */
    <E extends Entity> ModelType<E> getType(Object environmentHint);

    List<ModelTypeLoader> getModelTypeLoaders();
}
