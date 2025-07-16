package fr.esgi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Permettre l'accès aux fichiers uploadés via l'URL /uploads/**
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
        
        System.out.println("[LOG] Configuration des ressources statiques : /uploads/** -> file:uploads/");
    }
}
