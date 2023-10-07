package com.essaid.model.internal;


import com.essaid.model.Model;

public interface EntityState extends State {

    String PREFIX = Model.Internal.MODEL_KEY_PREFIX + ".entityState";

    String ENTITY_TYPE_KEY = PREFIX + ".entityType";
}
