package com.essaid.views.internal.impl;

import com.essaid.views.internal.ListWithFixedTags;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ArrayListWithFixedTags<E> extends ArrayList<E> implements ListWithFixedTags<E> {

  private Set<Integer> fixedTags = new HashSet<>();

  @Override
  public boolean isFixed(int index) {
    return fixedTags.contains(index);
  }

  @Override
  public void setFixed(int index) {
    fixedTags.add(index);
  }
}
