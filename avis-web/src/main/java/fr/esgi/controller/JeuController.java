package fr.esgi.controller;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.api.JeuService;
import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class JeuController {

    @Autowired
    private JeuService  jeuService;
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
            String fnfp = jeuService.ajouterImage(idJeu, image.getInputStream());
            System.out.println("File name with path: " + fnfp);
        } catch (IOException e) {
            throw new TechnicalException(e.getMessage());
        }
        return new ModelAndView("redirect:/jeux");
    }
}
