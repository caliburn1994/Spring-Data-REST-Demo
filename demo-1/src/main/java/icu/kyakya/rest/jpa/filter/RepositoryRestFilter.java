package icu.kyakya.rest.jpa.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static icu.kyakya.rest.jpa.config.Constants.TMP_SEARCH_PATH;

@Component
@Order(1)
@Slf4j
@RequiredArgsConstructor
public class RepositoryRestFilter extends OncePerRequestFilter {
    @Value("${spring.data.rest.basePath}")
    String basePath;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals(RequestMethod.GET.toString())) {
            filterChain.doFilter(request, response);
            return;
        }

        List<String> uriSplits = Arrays.stream(request.getRequestURI().replaceAll("^" + basePath, "").split("/"))
                .filter(s -> s.length() != 0)
                .collect(Collectors.toList());
        if (uriSplits.size() >= 2) {
            String resource = uriSplits.remove(0);
            String op = uriSplits.remove(0);
            if (op.equals("search")) {
                request.getRequestDispatcher(basePath+"/" + TMP_SEARCH_PATH + "/" + resource).forward(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}

