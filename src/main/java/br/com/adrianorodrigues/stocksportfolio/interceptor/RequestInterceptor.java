package br.com.adrianorodrigues.stocksportfolio.interceptor;

import br.com.adrianorodrigues.stocksportfolio.context.TokenContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    TokenContext tokenContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(mustAuthorizate(request)) {
            log.info("Bearer " + request.getHeader("Authorization"));
            tokenContext.fromBearerToken(request.getHeader("Authorization"));
        }
        return true;
    }

    private boolean mustAuthorizate(HttpServletRequest request) {
        return !request.getServletPath().contains("actuator/prometheus")
                && !request.getServletPath().contains("error");
    }

}