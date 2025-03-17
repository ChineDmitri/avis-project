package fr.esgi.port;

import fr.esgi.model.Editeur;
import fr.esgi.model.Jeu;

import java.util.List;
import java.util.Optional;

public interface EditeurRepository {

    Editeur save(Editeur editeur);

    List<Jeu> findEditorsWithoutGames();

    List<Editeur> findByNomContainingIgnoreCase(String nom);

    Editeur findByNom(String nom);

    Optional<Editeur> findByNomIgnoreCase(String nom);

    List<Editeur> findAll();

    Optional<Editeur> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
