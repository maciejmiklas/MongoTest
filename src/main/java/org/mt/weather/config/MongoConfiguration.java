package org.mt.weather.config;

import com.mongodb.MongoClient;
import org.mt.weather.service.intern.converter.LocalDateReadConverter;
import org.mt.weather.service.intern.converter.LocalDateWriteConverter;
import org.mt.weather.service.intern.converter.LocalTimeReadConverter;
import org.mt.weather.service.intern.converter.LocalTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Arrays;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "weatherDB";
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(), mongoConverter());
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new LocalDateReadConverter(),
                new LocalDateWriteConverter(),
                new LocalTimeReadConverter(),
                new LocalTimeWriteConverter()));
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo(), getDatabaseName());
    }

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        return mongoConverter;
    }
}
