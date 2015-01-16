package com.qorder.qorderws.mapper;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Wraps the real model mapper into @{link IMapper} representation interface.
 * As all clients use the {@code IMapper} interface, they remain decoupled from
 * the model mapper implementation, as well as it's respectful library.
 *
 * @author Grigorios
 */
@Component
public final class ObjectMapWrapper implements IMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    @PostConstruct
    private void configureMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        //Do not map if source instance is a null reference
        Condition notNull = (context) -> context.getSource() != null;
        modelMapper.getConfiguration().setPropertyCondition(notNull);
    }

    @Override
    public <S, T> T map(@NotNull S source, @NotNull T target) {
        modelMapper.map(source, target);
        return target;
    }

    @Override
    public <S, T> T mapWithResolver(@NotNull S source, @NotNull T target, IMapResolver<S, T> resolver) {
        return resolver.doMap(source, target);
    }

}
