package com.qorder.qorderws.mapper.resolver;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.constraints.NotNull;

/**
 * Wraps the real model mapper into @{link IMapper} representation interface.
 * As all clients use the {@code IMapper} interface, they remain decoupled from
 * the model mapper implementation, as well as it's respectful library.
 *
 * @author Grigorios
 */
public final class BasicMapWrapper<S, T> implements IMapResolver<S, T> {

    private final ModelMapper modelMapper = new ModelMapper();


    public BasicMapWrapper() {
        configureMapper();
    }

    private void configureMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        //Do not map if source instance is a null reference
        Condition notNull = (context) -> context.getSource() != null;
        modelMapper.getConfiguration().setPropertyCondition(notNull);
    }

    @Override
    public T doMap(@NotNull S source, @NotNull T target) {
        modelMapper.map(source, target);
        return target;
    }
}
