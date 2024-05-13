package com.essaid.views.adapter;

import com.essaid.views.internal.ViewHandler;
import com.essaid.views.session.ViewsSessionInternal;
import java.util.Optional;

public interface AdaptRequest {

  Object getAdaptee();

  /**
   * The actual type the client needs (but an {@link Adapter} is allowed to return a subtype). See
   * {@link #getAdaptedUsageType()} as a way to distinguish the needed type vs. the use of the
   * adapted object in this API or by the client.
   *
   * @return
   */
  Class<?> getAdaptedType();

  Class<?>[] getDefaultInterfaces();

  /**
   * This is a type hint to an {@link Adapter}. It might be needed in certain situations. For
   * example, an Adapter that is able to transform any value into a View needs to know if it should
   * try to handel the adapt request or not. In this case, A client that wants its value converted
   * to a view needs to set the category to {@link com.essaid.views.View} as a type hint for the
   * type of object to be adapted to. Another way to think about this is that this type hint is a
   * hint for the use of the adapted object rather than it's actual type, which is captured in the
   * {@link #getAdaptedType}
   *
   * <p>This does not necessary mean that the adapted object will also implement this type, and
   * the adapted object can be casted to this type. Consider this as a hint object between the
   * client and one or more {@link Adapter}s and (for now) we're using class objects as the hint
   * value for convenience. </p>
   *
   * It can be left null but this will likely affect {@link Adapter}s that need this information.
   * They will likely fall back to examining the {@link #getAdapted()} type to decide if
   * they should apply their logic or not.
   *
   * @return
   */
  Class<?> getAdaptedUsageType();

  ViewsSessionInternal getSession();

  ViewHandler getViewHandler();

  Optional<?> getAdapted();

  void setAdapted(Object adapted);
}
