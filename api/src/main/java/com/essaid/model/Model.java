package com.essaid.model;

import com.essaid.model.internalOld.ModelObject;

public interface Model<E, B, D, S>  {

    <I extends E> I create(Class<I> iface);


    Internal<E, B, D, S> internal();

    interface Internal<E, B, D, S> extends Model<E, B, D, S>, ModelObject {

        static final String MODEL_KEY_PREFIX = "model";

    }
}
