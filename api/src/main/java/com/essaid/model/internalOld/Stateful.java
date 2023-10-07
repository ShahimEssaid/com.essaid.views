package com.essaid.model.internalOld;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

/**
 * <ul>
 *     <li>State holds the state of an object.</li>
 *     <li>If it is not bound, the state is handled by the generic method resolvers</li>
 *     <ul>
 *         <li>The type tag will be the same as the client interface</li>
 *     </ul>
 *     <li>If the state is bound</li>
 *     <ul>
 *         <li>the bound type will return </li>
 *     </ul>
 * </ul>
 *
 */
public interface Stateful {

    Class<?> getType();

    Class<?> getBoundType();

    void setBoundType(Class<?> bountType) throws AlreadyBoundException;

    Optional<Stateful> getNestedState(Object nestedStateKey, boolean create);

    Object get(Object key);

    Object set(Object key, Object value);


    default boolean isBound() {
        return getBoundType() != null;
    }

}
