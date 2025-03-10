package fr.esgi.fx.avis.controller;

import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.service.JeuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Iterator;

@Controller
@AllArgsConstructor
public class JeuController {

    private JeuService jeuService;

    @GetMapping({ "jeux" })
    public ModelAndView getJeux(
            // Le premier paramètre, de type Pageable, correspond à une demande de page
            // Le second paramètre; de type HttpServletRequest, correspond à la requête HTTP reçue
            @PageableDefault(size = 10, sort = "nom", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jeux");

        Iterator<Order> iterator = pageable.getSort().iterator();
        StringBuilder sortBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            sortBuilder.append(order.getProperty());
            sortBuilder.append(',');
            sortBuilder.append(order.getDirection());
            if (iterator.hasNext()) {
                // Bricodage
                sortBuilder.append("&sort=");
            }
        }
        mav.addObject("sort", sortBuilder.toString());

        Page<Jeu> jeux = jeuService.recupererJeux(pageable);
        mav.addObject("pageDeJeux", jeux);

        Long dateHeureDebut = (Long) request.getAttribute("dateHeureDebut");
        mav.addObject("tempsDeTraitementEnMs", new Date().getTime() - dateHeureDebut);

        return mav;
    }

    @GetMapping("televersement")
    public ModelAndView getTeleversement(@RequestParam("ID") Long id) {
        ModelAndView mav = new ModelAndView("televersement");
        mav.addObject("jeu", jeuService.recupererJeu(id));
        return mav;
    }

    @PostMapping("televersement/{idJeu}")
    public ModelAndView postTeleversement(@PathVariable Long idJeu, @RequestParam("file") MultipartFile image) {
        jeuService.ajouterImage(idJeu, image);
        return new ModelAndView("redirect:/jeux");
    }
}
