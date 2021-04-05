//package icu.kyakya.rest.jpa.filter;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//@Order(1)
//@Slf4j
//@RequiredArgsConstructor
//public class ApiVersionFilter extends OncePerRequestFilter {
//
//    @Data
//    @Component
//    @ConfigurationProperties(prefix = "app.version")
//    public static class ApiVersionProperties {
//        private Map<String, List<String>> address;
//    }
//
//    @Value("${spring.data.rest.basePath}")
//    String basePath;
//
//    private final ApiVersionProperties apiVersionProperties;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//        String uri = request.getRequestURI();
//
//        List<String> uriSplits = Arrays.stream(uri.replaceAll("^" + basePath, "").split("/"))
//                .filter(s -> s.length() != 0)
//                .collect(Collectors.toList());
//
//        if (uriSplits.size() >= 2) {
//            String version = uriSplits.remove(0);
//            String resource = uriSplits.remove(0);
//
//            for (Map.Entry<String, List<String>> entry : apiVersionProperties.getAddress().entrySet()) {
//                String versionFromProp = entry.getKey();
//                List<String> resourceFromProp = entry.getValue();
//                if (versionFromProp.equals("all") && resourceFromProp.contains(resource)) {
//                    request.getRequestDispatcher(uri.replace("/" + version, "")).forward(request, response);
//                    return;
//                }
//                if (versionFromProp.equals(version) && resourceFromProp.contains(resource)) {
//                    request.getRequestDispatcher(uri.replace("/" + version, "")).forward(request, response);
//                    return;
//                }
//            }
//
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//
//}
//
