package org.mt.weather.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("org.mt.weather")
@Import(MongoConfiguration.class)
public class MtConfiguration {

}
