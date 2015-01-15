package com.qorder.qorderws.mapper;

import javax.validation.constraints.NotNull;

/**
 * Represents a knowledge transporter, between two instances.
 * The flow of information goes from source instance to target.
 *
 * @author Grigorios
 */
@FunctionalInterface
public interface IMapper {

	/**
	 * Passes information from source instance to target
	 * and returns the target
	 *
	 * @param source the instance used to get info from.
	 * @param target the instance used to set info to.
	 * @return T target instance
	 */
	<S, T> T map(@NotNull S source, @NotNull T target);
}
