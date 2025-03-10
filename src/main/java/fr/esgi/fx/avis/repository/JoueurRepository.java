package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Joueur;
import fr.esgi.fx.avis.dto.NbJoueursParAnnee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {

    @Query("""
            FROM Joueur j
            WHERE month(j.dateDeNaissance)=month(current date)
               AND day(j.dateDeNaissance)=day(current date) 
            """)
    List<Joueur> findJoueursCelebrantLeurAnniversaireAujourdhui();

    @Query("select j from Joueur j where j.dateDeNaissance = ?1")
    List<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance);

    List<Joueur> findTop10ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    List<Joueur> findTop1ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    // Joueurs nés en entre deux dates données en paramètre
    Page<Joueur> findByDateDeNaissanceBetween(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd, Pageable pageable);

    // Joueurs nés en entre deux dates données en paramètre
    long countByDateDeNaissanceBetween(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd);

    @Query("select j from Joueur j where j.dateDeNaissance = ?1 and j.dateDeNaissance = ?2")
    Page<Joueur> methodHQL(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd, Pageable pageable);

    // Comment obtenir le nombre de joueurs par année de naissance
    // Projection : SELECT new
    @Query("""
            SELECT new fr.esgi.fx.avis.dto.NbJoueursParAnnee(year(j.dateDeNaissance), count(*))
            FROM Joueur j
            GROUP BY year(j.dateDeNaissance)
            """)
    List<NbJoueursParAnnee> findNbJoueursParAnnee();

    // Requête pour obtenir les joueurs triés sur le
    // nombre d'avis décroissant
   @Query("""
            FROM Joueur j
            ORDER BY size(j.avis) DESC
           """)
   @Meta(comment="Récupère les joueurs triés sur le nombre d'avis décroissant")
    List<Joueur> findByNbAvisDesc();
}