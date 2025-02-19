package com.SocialNetSys.NetSys.Middlewares.Configuration;


import com.SocialNetSys.NetSys.Middlewares.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AuthMiddleware authMiddleware;

    @Autowired
    public WebMvcConfig(AuthMiddleware authMiddleware) {
        this.authMiddleware = authMiddleware;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authMiddleware)
                .addPathPatterns("/**")
                .excludePathPatterns("/v3/api-docs", "/swagger-ui/**"); // Exclua as rotas do Swagger, se aplicável
    }
}
