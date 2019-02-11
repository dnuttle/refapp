package net.nuttle.config;

import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * See: https://stackoverflow.com/questions/47792915/getting-jackson-parsing-error-while-serializing-aggregatedpage-in-spring-data-el
 * This still doesn't fix my problem with ES aggs, but probably needs tweaking
 * @author Dan
 *
 */

@Configuration
public class JacksonConfig   {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer changeKeyAsNumber() {
        return new Jackson2ObjectMapperBuilderCustomizer() {

            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.mixIn(StringTerms.Bucket.class, MixIn.class);
            }
        };
    }

}

abstract class MixIn {
    @JsonIgnore
    abstract public Number getKeyAsNumber();
}