package com.essaid.model;

public interface Element extends Modelled {


    default Internal internal() {
        return (Internal) this;
    }

    interface Internal extends Element {

        Element _getContainer();

        void _setContainer(Element container);

    }
}
