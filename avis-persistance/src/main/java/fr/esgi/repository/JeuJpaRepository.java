package fr.esgi.repository;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.entity.GenreEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.entity.PlateformeEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JeuJpaRepository extends JpaRepository<JeuEntity, Long> {

    JeuEntity findFirstByNom(String nom);

    List<JeuEntity> findByEditeur(EditeurEntity editeur);

    List<JeuEntity> findByEditeurNom(String nom);

    List<JeuEntity> findByEditeurAndGenre(EditeurEntity editeur, GenreEntity genre);

    List<JeuEntity> findTop5ByEditeurOrderByDateDeSortieDesc(EditeurEntity editeur);

    List<JeuEntity> findByEditeurAndGenreAndClassificationNom(EditeurEntity editeur, GenreEntity genre, String nom);

    List<JeuEntity> findByGenre(GenreEntity genre);

    List<JeuEntity> findByGenreNom(String nom);

    List<JeuEntity> findByNomLike(String nom);

    List<JeuEntity> findByNomLikeAndDateDeSortieBetween(String nom, LocalDate dateDebut, LocalDate dateFin);

    List<JeuEntity> findByEditeurAndNomLikeAndDateDeSortieBetween(EditeurEntity editeur, String nom, LocalDate dateDebut, LocalDate dateFin);

    List<JeuEntity> findAllByPlateformesContaining(PlateformeEntity plateforme);

    List<JeuEntity> findByPlateformesNom(String nom);

    // Comment récupérer un jeu par son nom et vérifier
    // que l'index a été utilisé ?
    Optional<JeuEntity> findByNom(String nom);

    List<JeuEntity> findByNomEndingWith(String filtre);

    boolean existsByNom(String nom);

    long countByEditeur(EditeurEntity editeur);

    @Override
    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    JeuEntity save(JeuEntity jeu);

    @Transactional
    long deleteByEditeur(EditeurEntity editeur);

    List<JeuEntity> findByPlateformes(PlateformeEntity plateforme);

    List<JeuEntity> findByDateDeSortieBetweenAndGenreAndImageNotNull(LocalDate dateDeSortieStart,
                                                                     LocalDate dateDeSortieEnd,
                                                                     GenreEntity genre);

    Optional<JeuEntity> findByNomIgnoreCase(String nom);

    @Query(
            """
                    FROM JeuEntity
                    ORDER BY rand()
                    """
    )
    List<JeuEntity> findGamesRandomlySorted();
}
