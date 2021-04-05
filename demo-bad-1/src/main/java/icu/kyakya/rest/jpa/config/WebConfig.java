package icu.kyakya.rest.jpa.config;

import icu.kyakya.rest.jpa.web.CustomRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        CustomRequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setRemoveSemicolonContent(false);
//        handlerMapping.setContentNegotiationManager(contentNegotiationManager());

        return handlerMapping;
    }

//    @Bean
//    public ContentNegotiationManager contentNegotiationManager() {
//        ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();
//        Map<String, MediaType> mediaTypes = new HashMap<>();
//        mediaTypes.put("json", MediaType.APPLICATION_JSON);
//        mediaTypes.put("html", MediaType.TEXT_HTML);
//        contentNegotiationManagerFactoryBean.addMediaTypes(mediaTypes);
//
//        contentNegotiationManagerFactoryBean.afterPropertiesSet();
//
//        return contentNegotiationManagerFactoryBean.getObject();
//    }
}
