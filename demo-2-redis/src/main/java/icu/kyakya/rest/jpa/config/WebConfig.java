package icu.kyakya.rest.jpa.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  not work because of bug. @see AddressInterceptor.java
 *
 *  @Configuration
 *  public class WebConfig implements WebMvcConfigurer {
 *  @Override
 *  public void addInterceptors(InterceptorRegistry registry) {
 *  registry.addInterceptor(new AddressInterceptor()).addPathPatterns("/**");
 *  }
 *  }
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

}


