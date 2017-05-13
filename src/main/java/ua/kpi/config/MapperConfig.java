package ua.kpi.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.kpi.mapper.LocalDateMapper;

/**
 * @author Mykola Yashchenko
 */
@Configuration
public class MapperConfig {

    @Bean
    public MapperFacade mapperFacade() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory
                .getConverterFactory()
                .registerConverter(new LocalDateMapper());
        return mapperFactory.getMapperFacade();
    }
}
