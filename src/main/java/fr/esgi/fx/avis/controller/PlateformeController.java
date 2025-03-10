package fr.esgi.fx.avis.controller;

import fr.esgi.fx.avis.service.PlateformeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class PlateformeController {

    private PlateformeService plateformeService;

    @GetMapping({ "plateformes" })
    public ModelAndView getPlateformes() {
        ModelAndView mav = new ModelAndView("plateformes");

        mav.addObject("plateformes", plateformeService.recupererPlateformes());

        return mav;
    }
}
