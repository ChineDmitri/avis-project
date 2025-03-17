package fr.esgi.port;

import fr.esgi.model.Avis;

import java.util.List;
import java.util.Optional;

public interface AvisRepository {

    Avis save(Avis avis);

    List<Avis> findAll();

    Optional<Avis> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

}
