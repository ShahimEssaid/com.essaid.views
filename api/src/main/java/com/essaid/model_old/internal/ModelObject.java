package com.essaid.model_old.internal;

import java.io.Serializable;

public interface ModelObject extends Serializable {

     default ModelObjectInternal internal(){
         return (ModelObjectInternal) this;
     }

    interface ModelObjectInternal extends ModelObject {
        ModelObject getContainer();
        void setContainer(ModelObject container);
        ObjectHandler objectHandler();
    }
}
