package icu.kyakya.rest.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JPAConfig {


    // https://docs.spring.io/spring-hateoas/docs/current/reference/html/#server.processors


//    @Bean
//    public RepresentationModelProcessor<EntityModel<Person>> personProcessor() {
//
//        return model -> {
//            model.add(of("http://localhost:8080/api/v1/people", "added-link"));
//            return model;
//        };
//    }
}
