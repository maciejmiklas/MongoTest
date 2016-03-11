package org.mt.weather.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mt.weather.service.intern.converter.LocalDateReadConverter;
import org.mt.weather.service.intern.converter.LocalDateWriteConverter;
import org.mt.weather.service.intern.converter.LocalTimeReadConverter;
import org.mt.weather.service.intern.converter.LocalTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
	return "weatherDB";
    }

    @Override
    public Mongo mongo() throws Exception {
	return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
	return new MongoTemplate(mongo(), getDatabaseName());
    }

    @Override
    public CustomConversions customConversions() {
	return new CustomConversions(Arrays.asList(
		new LocalDateReadConverter(),
		new LocalDateWriteConverter(),
		new LocalTimeReadConverter(),
		new LocalTimeWriteConverter()));
    }
}
