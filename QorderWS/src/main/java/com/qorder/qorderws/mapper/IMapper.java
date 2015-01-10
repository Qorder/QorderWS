package com.qorder.qorderws.mapper;

import javax.validation.constraints.NotNull;

/**
 * Represents a knowledge transporter, between two instances.
 * The flow of information goes from source instance to target.
 *
 * @param <S> type of source instance
 * @param <T> type of target instance
 * @author Grigorios
 */
@FunctionalInterface
public interface IMapper<S, T> {

	/**
	 * Passes information from source instance to target
	 * and returns the target
	 *
	 * @param source
	 * @param target
	 * @return T target instance
	 */
	T map(@NotNull S source, @NotNull T target);
}
