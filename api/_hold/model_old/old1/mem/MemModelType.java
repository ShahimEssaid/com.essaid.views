package com.essaid.model_old.old1.mem;

import com.essaid.model_old.old1.Entity;
import com.essaid.model_old.old1.Model;
import com.essaid.model_old.old1.ModelType;

public class MemModelType<E extends Entity, S> implements ModelType {
    @Override
    public Model<E, S> creteModel() {
        MemModel model = new MemModel();
        return model;
    }
}
