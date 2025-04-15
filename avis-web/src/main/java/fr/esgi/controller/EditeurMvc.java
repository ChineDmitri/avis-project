package fr.esgi.controller;

import fr.esgi.api.EditeurService;
import fr.esgi.model.Editeur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class EditeurMvc {

    @Autowired
    private EditeurService editeurService;

    @GetMapping("/hello")
    public String home(Model model) {
        return "hello";
    }

    @GetMapping({"editeurs", "index", "/"})
    public ModelAndView getEditeurs() {
        ModelAndView  mav      = new ModelAndView("editeurs"); // NOSONAR
        List<Editeur> editeurs = editeurService.recupererEditeurs();

        mav.addObject("editeurs", editeurs);

        return mav;
    }

    @PostMapping("editeurs")
    public ModelAndView rechercherEditeurs(@RequestParam("nom") String nom) {
        List<Editeur> editeurs = editeurService.recupererEditeursParNomContenant(nom);

        return new ModelAndView("editeurs")
                .addObject("editeurs", editeurs)
                .addObject("nom", nom);
    }

    @GetMapping("editeur")
    public ModelAndView afficherFormulaireEditeur(@ModelAttribute Editeur editeur) {
        return new ModelAndView("editeur");
    }

    @PostMapping("editeur")
    public ModelAndView sauvegarderEditeur(
            @Valid @ModelAttribute Editeur editeur,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return afficherFormulaireEditeur(editeur);
        }

        editeurService.ajouterEditeur(editeur);

        return new ModelAndView("redirect:/editeurs");
    }
}
