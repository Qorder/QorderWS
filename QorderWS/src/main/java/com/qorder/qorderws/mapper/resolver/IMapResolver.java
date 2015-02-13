package com.qorder.qorderws.mapper.resolver;

/**
 *
 * @author Grigorios
 * @param <S> type of source object
 * @param <T> type of target object
 */
@FunctionalInterface
public interface IMapResolver<S, T> {

    /**
     * Passes information from source instance to target and returns the target
     *
     * @param source the instance used to get info from.
     * @param target the instance used to set info to.
     * @return T target instance
     */
    T doMap(S source, T target);

}
