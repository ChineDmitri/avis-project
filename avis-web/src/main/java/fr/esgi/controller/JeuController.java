package fr.esgi.controller;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.api.JeuService;
import fr.esgi.api.EditeurService;
import fr.esgi.api.PlateformeService;
import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.Editeur;
import fr.esgi.model.Plateforme;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class JeuController {

    @Autowired
    private JeuService jeuService;
    @Autowired
    private EditeurService editeurService;
    @Autowired
    private PlateformeService plateformeService;
    @Autowired
    private PageAdapter pageAdapter;

    @GetMapping({"jeux"})
    public ModelAndView getJeux(
            @PageableDefault(
                    size = 10,
                    sort = "nom",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jeux");

        // Create sort string for the view (keeping existing functionality)
        Iterator<Sort.Order> iterator = pageable.getSort()
                                                .iterator();
        StringBuilder sortBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            sortBuilder.append(order.getProperty());
            sortBuilder.append(',');
            sortBuilder.append(order.getDirection());
            if (iterator.hasNext()) {
                sortBuilder.append("&sort=");
            }
        }
        mav.addObject("sort", sortBuilder.toString());

        // Convert Spring's Pageable to our custom PaginationParams
        PaginationParams paginationParams = pageAdapter.toPaginationParams(pageable);

        // Call the service with our custom pagination parameters
        CustomPagedResult<Jeu> jeux = jeuService.recupererJeux(paginationParams);

        // Add the result to the model
        mav.addObject("pageDeJeux", jeux);

        Long dateHeureDebut = (Long) request.getAttribute("dateHeureDebut");
        mav.addObject("tempsDeTraitementEnMs", new Date().getTime() - dateHeureDebut);

        return mav;
    }

    @GetMapping("televersement")
    public ModelAndView getTeleversement(@RequestParam("ID")
                                         Long id) {
        ModelAndView mav = new ModelAndView("televersement");
        mav.addObject("jeu", jeuService.recupererJeu(id));
        return mav;
    }

    @PostMapping("televersement/{idJeu}")
    public ModelAndView postTeleversement(@PathVariable
                                          Long idJeu,
                                          @RequestParam("file")
                                          MultipartFile image) throws
                                                               TechnicalException {
        try {
            String fnfp = jeuService.ajouterImage(idJeu, image);  // Utiliser la nouvelle méthode avec MultipartFile
            System.out.println("File name with path: " + fnfp);
            
            // Rediriger vers la page avec un message de succès
            ModelAndView mav = new ModelAndView("redirect:/jeux");
            mav.addObject("imageUploadSuccess", true);
            return mav;
        } catch (Exception e) {
            System.err.println("Erreur lors du téléversement: " + e.getMessage());
            e.printStackTrace();
            throw new TechnicalException("Erreur lors du téléversement: " + e.getMessage());
        }
    }

    @GetMapping("jeu")
    public ModelAndView afficherFormulaireJeu() {
        ModelAndView mav = new ModelAndView("jeu");
        
        // Créer un nouveau jeu vide pour éviter les problèmes avec @NonNull
        Jeu jeu = new Jeu();
        mav.addObject("jeu", jeu);
        
        // Récupérer les données nécessaires pour les sélections
        List<Editeur> editeurs = editeurService.recupererEditeurs();
        List<Plateforme> plateformes = plateformeService.recupererPlateformes();
        
        mav.addObject("editeurs", editeurs);
        mav.addObject("plateformes", plateformes);
        
        return mav;
    }

    @PostMapping("jeu")
    public ModelAndView sauvegarderJeu(
            @Valid @ModelAttribute Jeu jeu,
            BindingResult bindingResult,
            @RequestParam(value = "plateformeIds", required = false) List<Long> plateformeIds
    ) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("jeu");
            mav.addObject("jeu", jeu);
            
            // Récupérer les données nécessaires pour les sélections
            List<Editeur> editeurs = editeurService.recupererEditeurs();
            List<Plateforme> plateformes = plateformeService.recupererPlateformes();
            
            mav.addObject("editeurs", editeurs);
            mav.addObject("plateformes", plateformes);
            
            return mav;
        }

        try {
            // Associer les plateformes sélectionnées au jeu
            if (plateformeIds != null && !plateformeIds.isEmpty()) {
                List<Plateforme> plateformesSelectionnees = plateformeService.recupererPlateformes()
                        .stream()
                        .filter(p -> plateformeIds.contains(p.getId()))
                        .toList();
                jeu.setPlateformes(plateformesSelectionnees);
            }

            jeuService.ajouterJeu(jeu);
            return new ModelAndView("redirect:/jeux");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("jeu");
            mav.addObject("jeu", jeu);
            mav.addObject("errorMessage", "Erreur lors de l'ajout du jeu: " + e.getMessage());
            
            // Récupérer les données nécessaires pour les sélections
            List<Editeur> editeurs = editeurService.recupererEditeurs();
            List<Plateforme> plateformes = plateformeService.recupererPlateformes();
            
            mav.addObject("editeurs", editeurs);
            mav.addObject("plateformes", plateformes);
            
            return mav;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Editeur.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text != null && !text.trim().isEmpty()) {
                    try {
                        Long id = Long.valueOf(text);
                        Editeur editeur = editeurService.recupererEditeurs()
                                .stream()
                                .filter(e -> e.getId().equals(id))
                                .findFirst()
                                .orElse(null);
                        setValue(editeur);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                } else {
                    setValue(null);
                }
            }
        });
    }
}
