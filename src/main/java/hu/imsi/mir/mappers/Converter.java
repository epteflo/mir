package hu.imsi.mir.mappers;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toList;

public class Converter<S, T> {
    private final Class<S> sourceClass;
    private final Class<T> targetClass;
    private final Function<S, T> mapper;
    private final BiConsumer<S, T> merger;
    private final Function<List<S>, List<T>> listMapper;


    public Converter(final Class<S> sourceClass, final Class<T> targetClass, final Function<S, T> mapper) {
        this(sourceClass, targetClass, mapper, null, null);
    }

    public Converter(final Class<S> sourceClass, final Class<T> targetClass, final Function<S, T> mapper, final BiConsumer<S, T> merger, final Function<List<S>, List<T>> listMapper) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.mapper = mapper;
        this.merger = merger;
        this.listMapper = listMapper;
    }

    public T map(final S source) {
        if (mapper == null) {
            throw new UnsupportedOperationException(format("Mapping between {0} and {1} is not supported", sourceClass, targetClass));
        }
        return mapper.apply(source);
    }

    public void merge(final S source, final T onto) {
        if (merger == null) {
            throw new UnsupportedOperationException(format("Merging {0} into {1} is not supported", sourceClass, targetClass));
        }
        merger.accept(source, onto);
    }

    public List<T> mapList(final List<S> source) {
        if (mapper == null && listMapper == null) {
            throw new UnsupportedOperationException(format("Mapping between lists of {0} and {1} is not supported", sourceClass, targetClass));
        }
        if (listMapper != null) {
            return listMapper.apply(source);
        }
        if (source == null) {
            return null;
        }
        return source.stream().map(mapper).collect(toList());
    }

    public Class<S> getSourceClass() {
        return sourceClass;
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

}
