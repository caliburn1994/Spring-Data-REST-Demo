package icu.kyakya.rest.jpa.filter;

import icu.kyakya.rest.jpa.config.ApiVersionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Order(1)
@Slf4j
public class ApiVersionFilter extends OncePerRequestFilter {

    public ApiVersionFilter(ApiVersionProperties apiVersionProperties) {
        this.apiVersionProperties = apiVersionProperties;
    }

    @Value("${spring.data.rest.basePath}")
    String basePath;

    private final ApiVersionProperties apiVersionProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        List<String> uriSplits = Arrays.stream(uri.replaceAll("^" + basePath, "").split("/"))
                .filter(s -> s.length() != 0)
                .collect(Collectors.toList());

        if (uriSplits.size() >= 2) {
            String version = uriSplits.remove(0);
            String resource = uriSplits.remove(0);

            for (Map.Entry<String, List<String>> entry : apiVersionProperties.getAddress().entrySet()) {
                String v = entry.getKey();
                List<String> address = entry.getValue();
                if (v.equals(version) && address.contains(resource)) {
                    request.getRequestDispatcher(uri.replace("/" + version, "")).forward(request, response);
                    return;
                }
            }

        }

        filterChain.doFilter(request, response);

    }

}

