package fr.esgi.init;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/*
    * Cette classe permet d'ajouter des données initiales dans la base de données
    * TO-DO à implementer
 */
@Component
@AllArgsConstructor
@Profile({"DEV", "PROD"})
@Transactional(readOnly = true)
public class AjoutDonneesInitiales {

    /*private EditeurRepository        editeurRepository;
    private ClassificationRepository classificationRepository;
    private GenreRepository      genreRepository;
    private PlateformeRepository plateformeRepository;
    private JeuRepository        jeuRepository;
    private JoueurRepository     joueurRepository;
    private ModerateurRepository moderateurRepository;
    private AvisRepository avisRepository;
    private AvatarRepository avatarRepository;*/
    /*@PersistenceContext
    private EntityManager    entityManager;*/

    // Le fait de déclarer l'attribut en static va dispenser Spring de gérer l'objet
    private static Faker faker = new Faker(Locale.FRENCH);

    // @Override
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    // public void run(String... args) throws Exception {
    public void init() {
        ajouterEditeurs();
        ajouterClassifications();
        ajouterGenres();
        ajouterPlateformes();
        ajouterJeux();
        ajouterAvatars();
        ajouterJoueurs(100);
        ajouterModerateur();
        ajouterAvis(200);
        //afficherStatistiques();
    }

    private void ajouterAvatars() {
        // to-do
    }

    private void ajouterAvis(int nbAvisAAjouter) {
        // to-do
    }

    private void ajouterJoueurs(int nbJoueursAAjouter) {
        // to-do
    }

    private void ajouterModerateur() {
        // to-do
    }

    @Transactional(readOnly = true)
    void afficherStatistiques() {
        // to-do
    }

    private void ajouterJeux() {
        // to-do
    }

    private void ajouterPlateformes() {
        // to-do
    }

    private void ajouterGenres() {
        // to-do
    }

    @Transactional(readOnly = true)
    void ajouterClassifications() {
        // to-do
    }


    private void ajouterEditeurs() {
        // to-do
    }

}


