package com.qorder.qorderws.mapper;

public interface IMapper<S, T> {

	T map(S source, T target);
}
