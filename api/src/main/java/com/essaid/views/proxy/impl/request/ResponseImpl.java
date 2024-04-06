package com.essaid.views.proxy.impl.request;

import com.essaid.views.proxy.internal.Response;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class ResponseImpl implements Response {

  private Object value;

  @Override
  public void setValue(Object value) {
    if (this.value != null) {
      throw new IllegalStateException("Response value already set for response: " + this + " "
          + "with new value: " + value);
    }
    this.value = value;
  }

  @Override
  public Map<Object, Object> getMetadata(boolean create) {

    return null;
  }
}
