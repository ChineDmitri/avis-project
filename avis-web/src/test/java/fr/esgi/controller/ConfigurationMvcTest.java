package fr.esgi.controller;

import jakarta.servlet.MultipartConfigElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConfigurationMvcTest {

    private MultipartConfigElement multipartConfigElement;
    private ConfigurationMvc configurationMvc;

    @BeforeEach
    void setUp() {
        multipartConfigElement = mock(MultipartConfigElement.class);
        configurationMvc = new ConfigurationMvc(multipartConfigElement);
    }

    @Test
    void testGetConfiguration() {
        // Arrange
        long expectedMaxFileSize = 10485760L; // 10 MB
        when(multipartConfigElement.getMaxFileSize()).thenReturn(expectedMaxFileSize);

        // Act
        ModelAndView result = configurationMvc.getConfiguration();

        // Assert
        assertEquals("configuration", result.getViewName());
        assertEquals(expectedMaxFileSize, result.getModel().get("maxFileSize"));
    }
}