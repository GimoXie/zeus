package io.gimo.zeus.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OrikaConfig {
    @Bean
    public MapperFactory mapperFactory(){
        DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
        MapperFactory mapperFactory =builder.build();
        mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalDateConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalTimeConverter());
        return mapperFactory;
    }

    private class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
        @Override
        public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
            return LocalDateTime.from(source);
        }

        @Override
        public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
            return LocalDateTime.from(source);
        }
    }

    private class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
        @Override
        public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
            return LocalDate.from(source);
        }

        @Override
        public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
            return LocalDate.from(source);
        }
    }

    private class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
        @Override
        public LocalTime convertTo(LocalTime source, Type<LocalTime> destinationType, MappingContext mappingContext) {
            return LocalTime.from(source);
        }

        @Override
        public LocalTime convertFrom(LocalTime source, Type<LocalTime> destinationType, MappingContext mappingContext) {
            return LocalTime.from(source);
        }
    }

}