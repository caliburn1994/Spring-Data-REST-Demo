package icu.kyakya.rest.jpa.hateoas;

import icu.kyakya.rest.jpa.module.address.Address;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;
import java.util.Optional;

@ControllerAdvice
public class AllEntityBodyAdvice implements ResponseBodyAdvice {

    @Value("${spring.data.rest.basePath}")
    String basePath;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        System.out.println("In supports() method of " + getClass().getSimpleName());
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("In beforeBodyWrite() method of " + getClass().getSimpleName());

        if (Objects.equals(request.getMethod(), HttpMethod.GET)) {
            modifyHateoas(body, request);
        }

        return body;
    }

    private void modifyHateoas(Object body, ServerHttpRequest request) {
        Optional<String> tmpStr = Optional.of(request.getURI().getPath()).map(s -> s.replace(basePath, ""));
        String uri = tmpStr.filter(s -> s.endsWith("/")).map(s -> s.substring(0, s.length() - 1)).orElse(tmpStr.get());
        if (uri.equals("/address")) {
            System.out.println("do something for response");
            System.out.println(body);
        }
    }


}