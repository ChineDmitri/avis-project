package fr.esgi.repository;

import fr.esgi.entity.JoueurEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface JoueurJpaRepository extends JpaRepository<JoueurEntity, Long> {

    @Query("""
            FROM JoueurEntity j
            WHERE month(j.dateDeNaissance)=month(current date)
               AND day(j.dateDeNaissance)=day(current date) 
            """)
    List<JoueurEntity> findJoueursCelebrantLeurAnniversaireAujourdhui();

    @Query("select j from JoueurEntity j where j.dateDeNaissance = ?1")
    List<JoueurEntity> findByDateDeNaissance(LocalDate dateDeNaissance);

    List<JoueurEntity> findTop10ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    List<JoueurEntity> findTop1ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    // Joueurs nés en entre deux dates données en paramètre
    Page<JoueurEntity> findByDateDeNaissanceBetween(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd, Pageable pageable);

    // Joueurs nés en entre deux dates données en paramètre
    long countByDateDeNaissanceBetween(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd);

    @Query("select j from JoueurEntity j where j.dateDeNaissance = ?1 and j.dateDeNaissance = ?2")
    Page<JoueurEntity> methodHQL(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd, Pageable pageable);

    // Comment obtenir le nombre de joueurs par année de naissance
    // Projection : SELECT new
//    @Query("""
//            SELECT new fr.esgi.fx.avis.dto.NbJoueursParAnnee(year(j.dateDeNaissance), count(*))
//            FROM JoueurEntity j
//            GROUP BY year(j.dateDeNaissance)
//            """)
//    List<NbJoueursParAnnee> findNbJoueursParAnnee();

    // Requête pour obtenir les joueurs triés sur le
    // nombre d'avis décroissant
   @Query("""
            FROM JoueurEntity j
            ORDER BY size(j.avis) DESC
           """)
   @Meta(comment="Récupère les joueurs triés sur le nombre d'avis décroissant")
    List<JoueurEntity> findByNbAvisDesc();
}
