package fr.esgi.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.mockito.Mockito.*;

class WebConfigTest {

    @Test
    void shouldAddResourceHandlers() {
        // Arrange
        WebConfig webConfig = new WebConfig();
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
        ResourceHandlerRegistration registration = mock(ResourceHandlerRegistration.class);

        when(registry.addResourceHandler("/uploads/**")).thenReturn(registration);

        // Act
        webConfig.addResourceHandlers(registry);

        // Assert
        verify(registry).addResourceHandler("/uploads/**");
        verify(registration).addResourceLocations("file:/uploads/");
    }
}