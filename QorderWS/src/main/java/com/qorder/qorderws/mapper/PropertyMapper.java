package com.qorder.qorderws.mapper;

import com.qorder.qorderws.mapper.resolver.BasicMapWrapper;
import com.qorder.qorderws.mapper.resolver.EMapType;
import com.qorder.qorderws.mapper.resolver.IMapResolver;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Grigorios
 */
@Component
public class PropertyMapper<S1, T1> implements IMapper<S1, T1> {

    private static final Map<EMapType, IMapResolver> resolvers = new EnumMap<>(EMapType.class);
    private static EMapType mapType;

    static {
        resolvers.put(EMapType.Basic, new BasicMapWrapper<>());

        //explicit not in use yet
        resolvers.put(EMapType.Explicit, null);

        mapType = EMapType.Basic;
    }



    private final S1 source;
    private final T1 target;


    public PropertyMapper() {
        this.source = null;
        this.target = null;
    }

    private PropertyMapper(S1 source) {
        this.source = Objects.requireNonNull(source,"source can not be null");
        this.target = null;
    }

    private PropertyMapper(S1 source, T1 target) {
        this.source = Objects.requireNonNull(source,"source can not be null");
        this.target = Objects.requireNonNull(target, "target can not be null");
        doMap();
    }

    void doMap() {
            resolvers.get(mapType).doMap(source, target);
    }

    @Override
    public <S, T> void addResolver(IMapResolver<S, T> resolver) {
        throw new NotImplementedException();
    }

    @Override
    public IMapper<S1, T1> explicit() {
        mapType = EMapType.Explicit;
        return this;
    }

    @Override
    public <S> IMapper<S, T1> map(@NotNull S source) {
        return new PropertyMapper<>(source);
    }

    @Override
    public <T> IMapper<S1, T> to(@NotNull T target) {
        return new PropertyMapper<>(source, target);
    }

    @Override
    public T1 get() {
        return target;
    }
}
