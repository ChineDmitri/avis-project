package fr.esgi.fx.avis.controller;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.service.EditeurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class EditeurController {

    private EditeurService editeurService;

    @GetMapping({ "editeurs", "index", "/" })
    public ModelAndView getEditeurs() {
        ModelAndView mav = new ModelAndView("editeurs");

        mav.addObject("editeurs", editeurService.recupererEditeurs());

        return mav;
    }

    // Cette méthode traite les requêtes HTTP vers l'url /editeurs, ces requêtes utilisent la méthode POST
    @PostMapping("editeurs")
    public ModelAndView postEditeurs(@RequestParam("NOM") String nom) {

        ModelAndView mav = new ModelAndView("editeurs");
        mav.addObject("editeurs", editeurService.recupererEditeursParNomContenant(nom));
        mav.addObject("nom", nom);

        return mav;
    }

    @GetMapping( "editeur")
    public ModelAndView getEditeur(@ModelAttribute Editeur editeur) {
        ModelAndView mav = new ModelAndView("editeur");

        return mav;
    }

    @PostMapping( "editeur")
    public ModelAndView postEditeur(@Valid @ModelAttribute Editeur editeur, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getEditeur(editeur);
        }
        editeurService.ajouterEditeur(editeur);
        return new ModelAndView("redirect:/editeurs");
    }
}
