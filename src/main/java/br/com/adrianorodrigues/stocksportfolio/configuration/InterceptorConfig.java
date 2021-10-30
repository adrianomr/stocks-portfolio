package br.com.adrianorodrigues.stocksportfolio.configuration;

import br.com.adrianorodrigues.stocksportfolio.interceptor.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequiredArgsConstructor
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private final RequestInterceptor inperceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptor);
    }

}