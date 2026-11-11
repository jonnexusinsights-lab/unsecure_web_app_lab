package cr.ac.ucr.if0009.insecurepay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // [VULNERABILIDAD INTENCIONADA: A05:2021 - Security Misconfiguration]
    // Permite CORS indiscriminado con wildcard '*' permitiendo peticiones cross-origin no restringidas
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}

