package fr.esgi.init;

import com.github.javafaker.Faker;
import fr.esgi.entity.*;
import fr.esgi.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


/*
 * Cette classe permet d'ajouter des données initiales dans la base de données
 * TO-DO à implementer
 */
@Component
@AllArgsConstructor
@Profile({"DEV", "PROD"})
@Transactional(readOnly = true)
@Log4j2
public class AjoutDonneesInitiales {

    private EditeurJpaRepository        editeurRepository;
    private ClassificationJpaRepository classificationRepository;
    private GenreJpaRepository          genreRepository;
    private PlateformeJpaRepository     plateformeRepository;
    private JeuJpaRepository            jeuRepository;
    private JoueurJpaRepository         joueurRepository;
    private ModerateurJpaRepository     moderateurRepository;
    private AvisJpaRepository           avisRepository;
    private AvatarJpaRepository         avatarRepository;
    @PersistenceContext
    private EntityManager               entityManager;

    private static Faker faker = new Faker(Locale.FRENCH);

    // @Override
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
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
    }

    private void ajouterAvatars() {
        avatarRepository.save(new AvatarEntity("AvatarEntity 1"));
        avatarRepository.save(new AvatarEntity("AvatarEntity 2"));
    }

    private void ajouterAvis(int nbAvisAAjouter) {
        if (avisRepository.count() == 0) {
            Random             random  = new Random(); //NOSONAR
            List<JoueurEntity> joueurs = joueurRepository.findAll();
            for (int i = 0; i < nbAvisAAjouter; i++) {
                JoueurEntity JoueurEntity = joueurs.get(random.nextInt(joueurs.size())); // NOSONAR
                AvisEntity AvisEntity = new AvisEntity(faker.letterify("????????"), // NOSONAR
                                                       jeuRepository.findGamesRandomlySorted()
                                                                    .get(0),
                                                       JoueurEntity);
                AvisEntity.setNote(random.nextFloat(21));
                JoueurEntity.getAvis()
                            .add(AvisEntity);
                avisRepository.save(AvisEntity);
            }
        }
    }

    private void ajouterJoueurs(int nbJoueursAAjouter) {
        if (joueurRepository.count() == 0) {
            Calendar calendar = Calendar.getInstance();
            int      compteur = 0;
            while (compteur < nbJoueursAAjouter) {
                compteur++;
                calendar.set(1940, 1, 1);
                Date dateDebut = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.set(2003, 1, 1);
                Date dateFin = calendar.getTime();
                Date dateAleatoire = faker.date()
                                          .between(dateDebut, dateFin);
                calendar.setTime(dateAleatoire);
            }

            joueurRepository.save(JoueurEntity.builder()
                                              .pseudo("test")
                                              .motDePasse("anniversaire")
                                              .email("test@m2iformation.fr")
                                              .dateDeNaissance(LocalDate.of(1999,
                                                                            LocalDate.now()
                                                                                     .getMonthValue(),
                                                                            LocalDate.now()
                                                                                     .getDayOfMonth()))
                                              .build());
        }
    }

    private void ajouterModerateur() {
    }

    //    @Transactional(readOnly = true)
    //    void afficherStatistiques() {
    //
    //        // méthode qui renvoie les plateformes dont le nom contient ce qui est donné en paramètre
    //        System.out.println("Méthode qui renvoie les plateformes dont le nom contient ce qui est donné en paramètre");
    //        plateformeRepository.findByNomContaining("Sta")
    //                            .forEach(System.out::println);
    //
    //        // méthode qui renvoie les éditeurs n'ayant pas encore édité de jeux
    //        System.out.println("Méthode qui renvoie les éditeurs n'ayant pas encore édité de jeux");
    //        editeurRepository.findEditorsWithoutGames()
    //                         .forEach(e -> System.out.println(e.getNom()));
    //
    //        // R5 : méthode par dérivation qui renvoie les jeux disponibles sur la PlateformeEntity donnée en paramètre
    //        System.out.println("Méthode par dérivation qui renvoie les jeux disponibles sur la PlateformeEntity donnée en paramètre");
    //        jeuRepository.findByPlateformes(plateformeRepository.findByNom("Nintendo Wii"))
    //                     .forEach(p -> System.out.println(p.getNom()));
    //    }

    private void ajouterJeux() {
        if (jeuRepository.count() == 0) {
            EditeurEntity nintendo         = editeurRepository.findByNom("Nintendo"); //NOSONAR
            EditeurEntity ubisoft          = editeurRepository.findByNom("Ubisoft"); //NOSONAR
            EditeurEntity riot             = editeurRepository.findByNom("Riot Games"); //NOSONAR
            EditeurEntity ankama           = editeurRepository.findByNom("Ankama"); //NOSONAR
            EditeurEntity bioWare          = editeurRepository.findByNom("BioWare"); //NOSONAR
            EditeurEntity cdProjeckRed     = editeurRepository.findByNom("CD Projekt Red"); //NOSONAR
            EditeurEntity blizzard         = editeurRepository.findByNom("Blizzard"); //NOSONAR
            EditeurEntity fromSoftware     = editeurRepository.findByNom("FromSoftware"); //NOSONAR
            EditeurEntity naughtyDog       = editeurRepository.findByNom("Naughty Dog"); //NOSONAR
            EditeurEntity hazelightStudios = editeurRepository.findByNom("Hazelight Studios"); //NOSONAR
            EditeurEntity idSoftware       = editeurRepository.findByNom("idSoftware"); //NOSONAR

            GenreEntity moba = genreRepository.findByNom("MOBA (Multiplayer online battle arena)");
            GenreEntity rpg  = genreRepository.findByNom("RPG (Role-playing game))");

            JeuEntity JeuEntity = new JeuEntity("Animal Crossing New Horizons", LocalDate.of(2020, 3, 20), nintendo);
            JeuEntity.setPlateformes(Arrays.asList(plateformeRepository.findAll()
                                                                       .get(0),
                                                   plateformeRepository.findAll()
                                                                       .get(1)));
            jeuRepository.save(JeuEntity);

            jeuRepository.save(new JeuEntity("Zelda Tears of the Kingdom", LocalDate.of(2023, 5, 12), nintendo));
            jeuRepository.save(new JeuEntity("Assassin's Creed Valhalla", LocalDate.of(2020, 11, 10), ubisoft));

            jeuRepository.save(new JeuEntity("Warframe"));
            jeuRepository.save(new JeuEntity("Final Fantasy VIII"));
            jeuRepository.save(new JeuEntity("Monster Hunter:World"));
            jeuRepository.save(new JeuEntity("Xenoblade Chronicles"));
            jeuRepository.save(new JeuEntity("Nier:Automata"));
            jeuRepository.save(new JeuEntity("Lost Ark"));
            jeuRepository.save(new JeuEntity("Aion"));
            jeuRepository.save(new JeuEntity("Métin 2"));
            jeuRepository.save(new JeuEntity("Tera"));
            jeuRepository.save(new JeuEntity("Tunic"));
            jeuRepository.save(new JeuEntity("Satisfactory"));
            jeuRepository.save(new JeuEntity("Valorant"));
            jeuRepository.save(new JeuEntity("Octopath Travellers"));
            jeuRepository.save(new JeuEntity("Minecraft"));
            jeuRepository.save(new JeuEntity("Outer Wild"));
            jeuRepository.save(new JeuEntity("Strays"));
            jeuRepository.save(new JeuEntity("Nier:Replicant"));

            jeuRepository.save(new JeuEntity("The last of us part II", editeurRepository.findByNom("Naughty Dog")));
            jeuRepository.save(new JeuEntity("GTA V", editeurRepository.findByNom("Rockstar")));
            jeuRepository.save(new JeuEntity("Splinter cell", editeurRepository.findByNom("Ubisoft")));

            jeuRepository.save(new JeuEntity("Mario Kart 8 ", "JeuEntity de course", LocalDate.of(2014, 5, 29),
                                             editeurRepository.findByNom("Nintendo")));
            jeuRepository.save(new JeuEntity("FIFA 2022", "JeuEntity de simulation de football", LocalDate.of(2021, 9, 27),
                                             editeurRepository.findByNom("Electronic Arts")));

            jeuRepository.save(new JeuEntity("League Of Legends", LocalDate.of(2009, 10, 27), riot, moba));
            jeuRepository.save(new JeuEntity("Dofus", LocalDate.of(2004, 9, 1), ankama, rpg));

            jeuRepository.save(new JeuEntity("Call of Duty", editeurRepository.findByNom("Activision")));
            jeuRepository.save(new JeuEntity("EVE", editeurRepository.findByNom("CCP")));
            jeuRepository.save(new JeuEntity("The Elder Scrolls : Skyrim", editeurRepository.findByNom("Bethesda")));

            jeuRepository.save(new JeuEntity("Dragon Age: Inquisition", LocalDate.of(2014, 11, 21), bioWare));
            jeuRepository.save(new JeuEntity("The Witcher 3: Wild Hunt", LocalDate.of(2015, 5, 24), cdProjeckRed));
            jeuRepository.save(new JeuEntity("Overwatch", LocalDate.of(2016, 11, 21), blizzard));
            jeuRepository.save(new JeuEntity("The Legend of Zelda: Breath of the Wild", LocalDate.of(2017, 3, 3), nintendo));
            jeuRepository.save(new JeuEntity("God of War", LocalDate.of(2018, 4, 4), ubisoft));
            jeuRepository.save(new JeuEntity("Sekiro: Shadows Die Twice", LocalDate.of(2019, 3, 22), fromSoftware));
            jeuRepository.save(new JeuEntity("The Last of Us Part II", LocalDate.of(2020, 6, 19), naughtyDog));
            jeuRepository.save(new JeuEntity("It Takes Two", LocalDate.of(2021, 11, 4), hazelightStudios));
            jeuRepository.save(new JeuEntity("Elden Ring", LocalDate.of(2022, 2, 25), fromSoftware));

            jeuRepository.save(new JeuEntity("Doom eternal", LocalDate.of(2020, 3, 20), idSoftware));

            jeuRepository.save(new JeuEntity("Palworld", LocalDate.of(2024, 1, 19), idSoftware));

            jeuRepository.save(new JeuEntity("Pikmin", LocalDate.of(2001, 10, 26), editeurRepository.findByNom("Nintendo")));

            jeuRepository.save(new JeuEntity("Halo 5", LocalDate.of(2015, 10, 27), editeurRepository.findByNom("Microsoft")));
        }
    }

    private void ajouterPlateformes() {
        if (plateformeRepository.count() == 0) {
            plateformeRepository.save(new PlateformeEntity("Amstrad CPC"));
            plateformeRepository.save(new PlateformeEntity("Nintendo Wii"));
            plateformeRepository.save(new PlateformeEntity("Nintendo Wii U"));
            plateformeRepository.save(new PlateformeEntity("Nintendo Switch"));
            plateformeRepository.save(new PlateformeEntity("Windows"));
            plateformeRepository.save(new PlateformeEntity("MacOS"));
            plateformeRepository.save(new PlateformeEntity("Steam"));
            plateformeRepository.save(new PlateformeEntity("Neo-Geo"));
            plateformeRepository.save(new PlateformeEntity("PlayStation 1"));
            plateformeRepository.save(new PlateformeEntity("PlayStation 2"));
            plateformeRepository.save(new PlateformeEntity("PlayStation 3"));
            plateformeRepository.save(new PlateformeEntity("PlayStation 4"));
            plateformeRepository.save(new PlateformeEntity("PlayStation 5"));
            plateformeRepository.save(new PlateformeEntity("PlayStation Vita"));
            plateformeRepository.save(new PlateformeEntity("PSP"));
            plateformeRepository.save(new PlateformeEntity("Sega Dreamcast"));
            plateformeRepository.save(new PlateformeEntity("Sega Mastersystem"));
            plateformeRepository.save(new PlateformeEntity("Sega Saturn"));
            plateformeRepository.save(new PlateformeEntity("Xbox One"));
            plateformeRepository.save(new PlateformeEntity("Xbox One Series"));
            plateformeRepository.save(new PlateformeEntity("Xbox 360"));
            plateformeRepository.save(new PlateformeEntity("Amiga"));
            plateformeRepository.save(new PlateformeEntity("Android"));
            plateformeRepository.save(new PlateformeEntity("Atari 8-bit"));
            plateformeRepository.save(new PlateformeEntity("Atari Jaguar"));
            plateformeRepository.save(new PlateformeEntity("Commodore 64"));
            plateformeRepository.save(new PlateformeEntity("Game Boy"));
            plateformeRepository.save(new PlateformeEntity("Game Boy Color"));
            plateformeRepository.save(new PlateformeEntity("Game Boy Advance"));
            plateformeRepository.save(new PlateformeEntity("Game Boy Advance SP"));
            plateformeRepository.save(new PlateformeEntity("NES"));
            plateformeRepository.save(new PlateformeEntity("PC-Engine"));
            plateformeRepository.save(new PlateformeEntity("SNES"));
            plateformeRepository.save(new PlateformeEntity("Nintendo 3DS"));
            plateformeRepository.save(new PlateformeEntity("Nintendo 64"));
            plateformeRepository.save(new PlateformeEntity("Nintendo DS"));
            plateformeRepository.save(new PlateformeEntity("Nintendo Gamecube"));
        }
    }

    private void ajouterGenres() {
        if (genreRepository.count() == 0) {
            genreRepository.save(GenreEntity.builder()
                                            .nom("FPS (First person shooter)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("TS (real-time strategy)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("RPG (Role-playing game)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Simulation")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Gestion")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("TPS (Third person shooter)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Digital collectible card game")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("MOBA (Multiplayer online battle arena)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Hack n Slash")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Action/Aventure")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Point and click")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Plates-formes")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("4X (eXplore, eXpand, eXploit and eXterminate)")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Tactical RPG")
                                            .build());
            genreRepository.save(GenreEntity.builder()
                                            .nom("Action RPG")
                                            .build());
        }
    }

    @Transactional(readOnly = true)
    void ajouterClassifications() {
        if (classificationRepository.count() == 0) {
            classificationRepository.save(new ClassificationEntity("PG 3", "0000FF"));
            classificationRepository.save(new ClassificationEntity("PG 7", "00FF00"));
            classificationRepository.save(new ClassificationEntity("PG 12", "FF0000"));
            classificationRepository.save(new ClassificationEntity("PG 16", "FFFF00"));
            classificationRepository.save(new ClassificationEntity("PG 18", "00FFFF"));
            classificationRepository.save(new ClassificationEntity("Aucune", "FFFFFF"));
        }
    }


    private void ajouterEditeurs() {
        if (editeurRepository.count() == 0) {
            editeurRepository.save(new EditeurEntity("Activision"));
            editeurRepository.save(new EditeurEntity("Amazon Games"));
            editeurRepository.save(new EditeurEntity("Ankama"));
            editeurRepository.save(new EditeurEntity("Bandai Namco"));
            editeurRepository.save(new EditeurEntity("Bethesda"));
            editeurRepository.save(new EditeurEntity("BioWare"));
            editeurRepository.save(new EditeurEntity("Blizzard"));
            editeurRepository.save(new EditeurEntity("Capcom"));
            editeurRepository.save(new EditeurEntity("CCP"));
            editeurRepository.save(new EditeurEntity("CD Projekt Red"));
            editeurRepository.save(new EditeurEntity("Davilex"));
            editeurRepository.save(new EditeurEntity("Digital Extreme"));
            editeurRepository.save(new EditeurEntity("Electronic Arts"));
            editeurRepository.save(new EditeurEntity("Epic Games"));
            editeurRepository.save(new EditeurEntity("FromSoftware"));
            editeurRepository.save(new EditeurEntity("Hazelight Studios"));
            editeurRepository.save(new EditeurEntity("idSoftware"));
            editeurRepository.save(new EditeurEntity("Microsoft"));
            editeurRepository.save(new EditeurEntity("MonolithSoftware"));
            editeurRepository.save(new EditeurEntity("Naughty Dog"));
            editeurRepository.save(new EditeurEntity("Nintendo"));
            editeurRepository.save(new EditeurEntity("Riot Games"));
            editeurRepository.save(new EditeurEntity("Rockstar"));
            editeurRepository.save(new EditeurEntity("Sega"));
            editeurRepository.save(new EditeurEntity("Square Enix"));
            editeurRepository.save(new EditeurEntity("Tencent"));
            editeurRepository.save(new EditeurEntity("Ubisoft"));
            editeurRepository.save(new EditeurEntity("Ultra Software"));
            editeurRepository.save(new EditeurEntity("Valve"));
            editeurRepository.save(new EditeurEntity("Wildcard"));
        }
    }
}


