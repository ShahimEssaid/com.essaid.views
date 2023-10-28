package com.essaid.model;

public interface Element extends Modelled {


    default Internal internal() {
        return (Internal) this;
    }

    interface Internal extends Element {

        Element getContainer();

        void setContainer(Element container);

    }
}
