package fr.esgi.fx.avis.controller;

import fr.esgi.fx.avis.business.Editeur;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class StatistiquesController {

//    @PersistenceContext
//    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    @GetMapping(value="/statistiques")
    public ModelAndView getConfiguration() {
        ModelAndView mav = new ModelAndView("statistiques");

        /*
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();
        */
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();

        String[] queries = stats.getQueries();
        mav.addObject("queries", queries);

        String[] entityNames = stats.getEntityNames();
        mav.addObject("entityNames", entityNames);

        mav.addObject("cachedData", sessionFactory.getCache().containsEntity(Editeur.class, 1L));
        mav.addObject("stats", stats);
        mav.addObject("currentTime", LocalDateTime.now());
        return mav;
    }
}
