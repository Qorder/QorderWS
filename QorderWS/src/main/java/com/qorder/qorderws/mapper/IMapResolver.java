package com.qorder.qorderws.mapper;

/**
 *
 * @author Grigorios
 */
@FunctionalInterface
public interface IMapResolver<S,T> {

    T doMap(S source, T target);

}
