package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.business.Genre;
import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.business.Plateforme;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JeuRepository extends JpaRepository<Jeu, Long> {

    Jeu findFirstByNom(String nom);

    List<Jeu> findByEditeur(Editeur editeur);

    List<Jeu> findByEditeurNom(String nom);

    List<Jeu> findByEditeurAndGenre(Editeur editeur, Genre genre);

    List<Jeu> findTop5ByEditeurOrderByDateDeSortieDesc(Editeur editeur);

    List<Jeu> findByEditeurAndGenreAndClassificationNom(Editeur editeur, Genre genre, String nom);

    List<Jeu> findByGenre(Genre genre);

    List<Jeu> findByGenreNom(String nom);

    List<Jeu> findByNomLike(String nom);

    List<Jeu> findByNomLikeAndDateDeSortieBetween(String nom, LocalDate dateDebut, LocalDate dateFin);

    List<Jeu> findByEditeurAndNomLikeAndDateDeSortieBetween(Editeur editeur, String nom, LocalDate dateDebut, LocalDate dateFin);

    List<Jeu> findAllByPlateformesContaining(Plateforme plateforme);

    List<Jeu> findByPlateformesNom(String nom);

    // Comment récupérer un jeu par son nom et vérifier
    // que l'index a été utilisé ?
    Optional<Jeu> findByNom(String nom);

    List<Jeu> findByNomEndingWith(String filtre);

    boolean existsByNom(String nom);

    long countByEditeur(Editeur editeur);

    @Override
    @Transactional
    @Lock(value= LockModeType.PESSIMISTIC_WRITE)
    Jeu save(Jeu jeu);

    @Transactional

    long deleteByEditeur(Editeur editeur);

    List<Jeu> findByPlateformes(Plateforme plateforme);

    List<Jeu> findByDateDeSortieBetweenAndGenreAndImageNotNull(LocalDate dateDeSortieStart, LocalDate dateDeSortieEnd, Genre genre);

    Optional<Jeu> findByNomIgnoreCase(String nom);

    @Query("""
            FROM Jeu
            ORDER BY rand()
            """)
    List<Jeu> findGamesRandomlySorted();
}