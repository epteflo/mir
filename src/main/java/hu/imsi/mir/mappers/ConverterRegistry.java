package hu.imsi.mir.mappers;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

public class ConverterRegistry {
    final List<Converter<?, ?>> converters;

    public ConverterRegistry(final List<Converter<?, ?>> converters) {
        this.converters = converters;
    }

    public <S, T> Optional<Converter<S, T>> maybeGetConverter(final Class<S> sourceClass, final Class<T> targetClass) {
        //noinspection unchecked
        return converters
                .stream()
                .filter(c -> sourceClass.equals(c.getSourceClass()))
                .filter(c -> targetClass.equals(c.getTargetClass()))
                .map(c -> (Converter<S, T>) c)
                .findFirst();
    }

    public <S, T> Converter<S, T> getConverter(final Class<S> sourceClass, final Class<T> targetClass) {
        return maybeGetConverter(sourceClass, targetClass)
                .orElseThrow(() -> new IllegalStateException(format("No converter defined for classes ({0},{1})", sourceClass, targetClass)));
    }

}
