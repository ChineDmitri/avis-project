package fr.esgi.port;

import fr.esgi.model.Editeur;
import fr.esgi.model.Genre;
import fr.esgi.model.Jeu;
import fr.esgi.model.Plateforme;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JeuRepository {

    Jeu findById(Long id);

    List<Jeu> findAll();

    CustomPagedResult<Jeu> findAll(PaginationParams paginationParams);

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

    Optional<Jeu> findByNom(String nom);

    List<Jeu> findByNomEndingWith(String filtre);

    boolean existsByNom(String nom);

    long countByEditeur(Editeur editeur);

    Jeu save(Jeu jeu);

    long deleteByEditeur(Editeur editeur);

    List<Jeu> findByPlateformes(Plateforme plateforme);

    List<Jeu> findByDateDeSortieBetweenAndGenreAndImageNotNull(LocalDate dateDeSortieStart, LocalDate dateDeSortieEnd, Genre genre);

    Optional<Jeu> findByNomIgnoreCase(String nom);

    List<Jeu> findGamesRandomlySorted();
}
