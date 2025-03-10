package fr.esgi.fx.avis.controller;

import jakarta.servlet.MultipartConfigElement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ConfigurationController {

    private MultipartConfigElement multipartConfigElement;

    @GetMapping(value="/configuration")
    public ModelAndView getConfiguration() {
        ModelAndView mav = new ModelAndView("configuration");
        mav.addObject("maxFileSize", multipartConfigElement.getMaxFileSize());

        return mav;
    }
}
