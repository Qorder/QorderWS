package com.qorder.qorderws.mapper;

import com.qorder.qorderws.mapper.resolver.IMapResolver;

/**
 * Represents a knowledge transporter, between two instances. The flow of
 * information goes from source instance to target.
 *
 * @author Grigorios
 */
public interface IMapper<S1, T1> {

    /**
     * Enables explicit mapping option
     * @return implementation tha uses explicit mapping
     */
    IMapper<S1,T1> explicit();

    /**
     * Appends an resolver for explicit mapping mode
     * @param resolver an explicit mapping resolver
     * @param <S> type of source object
     * @param <T> type of target object
     */
    <S, T> void addResolver(IMapResolver<S, T> resolver);

    /**
     * Type safe method that sets an object as source of this mapping
     * @param   source instance that will be used as source
     * @param   <S> type of source object
     * @return  a mapper with provided object as source
     */
    <S> IMapper<S, T1> map(S source);

    /**
     * Type safe method that sets an object as target of this mapping
     * @param   target instance that will be used as target
     * @param   <T> type of target object
     * @return  a mapper with provided object as target
     */
    <T> IMapper<S1, T> to(T target);

    /**
     * Returns the provided target object of this mapping
     * @return the mapped object instance
     */
    T1 get();

}
