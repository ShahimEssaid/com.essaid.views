package com.essaid.views.internal;

import java.util.List;

public interface ListWithFixedTags<E> extends List<E> {

  boolean isFixed(int index);

  void setFixed(int index);

}
