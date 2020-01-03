package hu.imsi.mir.configuration;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.mappers.ConverterRegistry;
import hu.imsi.mir.mappers.MuseumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConverterConfiguration {

    @Bean
    public Converter<RsMuseum, Museum> fromRsMuseumConverter(final MuseumMapper museumMapper) {
        return new Converter<>(RsMuseum.class, Museum.class, museumMapper::toInnerIn);
    }

    @Bean
    public Converter<Museum, HMuseum> fromMuseumConverter(final MuseumMapper museumMapper) {
        return new Converter<>(Museum.class, HMuseum.class, museumMapper::toEntity, museumMapper::mergeOnto, null);
    }

    @Bean
    public Converter<HMuseum, Museum> fromHMuseumConverter(final MuseumMapper museumMapper) {
        return new Converter<>(HMuseum.class, Museum.class, museumMapper::toInner);
    }

    @Bean
    public ConverterRegistry converterRegistry(final List<Converter<?, ?>> converters) {
        return new ConverterRegistry(converters);
    }
}
