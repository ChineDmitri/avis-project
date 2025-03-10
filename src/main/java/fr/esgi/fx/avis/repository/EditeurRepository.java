package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.business.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EditeurRepository extends JpaRepository<Editeur, Long> {
  @Query("""
          FROM Editeur e
          WHERE size(e.jeux)=0
          """)
  List<Jeu> findEditorsWithoutGames();

  List<Editeur> findByNomContainingIgnoreCase(String nom);

  Editeur findByNom(String nom);

  Optional<Editeur> findByNomIgnoreCase(String nom);

}