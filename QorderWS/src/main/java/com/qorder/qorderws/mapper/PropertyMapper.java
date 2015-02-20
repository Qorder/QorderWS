package com.qorder.qorderws.mapper;

import com.qorder.qorderws.mapper.resolver.BasicResolverWrapper;
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
public final class PropertyMapper<S1, T1> implements IMapper<S1, T1> {

    private static final Map<EMapType, IMapResolver> resolvers = new EnumMap<>(EMapType.class);

    static {
        resolvers.put(EMapType.Basic, new BasicResolverWrapper<>());

        //explicit not in use yet
        resolvers.put(EMapType.Explicit, null);
    }



    private final S1 source;
    private final T1 target;

    private EMapType mapType;

    public PropertyMapper() {
        this.source = null;
        this.target = null;

        this.mapType  = EMapType.Basic;
    }

    private PropertyMapper(S1 source, EMapType mapType) {
        this.source = Objects.requireNonNull(source,"source can not be null");
        this.target = null;

        this.mapType = mapType;
    }

    private PropertyMapper(S1 source, T1 target, EMapType mapType) {
        this.source = Objects.requireNonNull(source,"source can not be null");
        this.target = Objects.requireNonNull(target, "target can not be null");

        this.mapType = mapType;
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
        return new PropertyMapper<>(source, mapType);
    }

    @Override
    public <T> IMapper<S1, T> to(@NotNull T target) {
        return new PropertyMapper<>(source, target, mapType);
    }

    @Override
    public T1 get() {
        return target;
    }
}
