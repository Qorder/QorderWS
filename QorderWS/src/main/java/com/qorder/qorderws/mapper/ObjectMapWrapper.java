package com.qorder.qorderws.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Grigorios
 */
@Component
public class ObjectMapWrapper implements IMapper {

    private final ModelMapper modelMapper = new ModelMapper();


    @PostConstruct
    private void configureMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    @Override
    public <S, T> T map(@NotNull S source, @NotNull T target) {
        modelMapper.map(source, target);
        //TODO: After completed implementation, use the above validation method
        // modelMapper.validate();
        return target;
    }

}
