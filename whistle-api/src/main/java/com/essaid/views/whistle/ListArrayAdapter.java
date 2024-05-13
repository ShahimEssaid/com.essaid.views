package com.essaid.views.whistle;

import com.essaid.views.View;
import com.essaid.views.internal.ListWithFixedTags;
import com.essaid.views.internal.ViewHandler;
import com.google.cloud.verticals.foundations.dataharmonization.data.Array;
import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import com.google.cloud.verticals.foundations.dataharmonization.data.NullData;
import com.google.cloud.verticals.foundations.dataharmonization.data.path.Path;
import java.util.Collections;
import javax.annotation.Nonnull;

public class ListArrayAdapter implements Array {

  private final ListWithFixedTags<Object> list;
  private final ViewHandler viewHandler;

  public ListArrayAdapter(ListWithFixedTags<Object> list, ViewHandler viewHandler) {
    this.list = list;
    this.viewHandler = viewHandler;
  }

  @Nonnull
  @Override
  public Data getElement(int index) {
    if (index >= size()) return NullData.instance;

    Object o = list.get(index);
    if(o instanceof View){
      View view = (View) o;
      return (Data) view.__viewHandler();
    }
    throw new UnsupportedOperationException("For now, list items have to be View instances.");
  }

  @Override
  public Array setElement(int index, @Nonnull Data value) {
    if ( !(value instanceof View)){
      throw new UnsupportedOperationException("For now, list items have to be View instances.");
    }

    if (index>= size()){
      list.addAll(Collections.nCopies(index - size() + 1, null));
    }
    list.set(index, value);
    return this;
  }

  @Override
  public Array setFixedElement(int index, @Nonnull Data value) {
    setElement(index, value);
    list.setFixed(index);
    return this;
  }

  @Override
  public boolean isFixed(int index) {
    return list.isFixed(index);
  }

  @Override
  public int size() {
    return list.size();
  }


  @Override
  public Array getThrough(Path remainingPath) {
    return null;
  }

  @Override
  public Array flatten() {
    return null;
  }

  @Override
  public Data deepCopy() {
    return null;
  }

  @Override
  public boolean isWritable() {
    return false;
  }
}
