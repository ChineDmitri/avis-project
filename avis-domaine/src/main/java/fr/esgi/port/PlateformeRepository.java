package fr.esgi.port;

import fr.esgi.model.Plateforme;

import java.util.List;

public interface PlateformeRepository {
    List<Plateforme> findByNomContaining(String filtre);

    Plateforme findByNom(String nom);

    void delete(Plateforme plateforme);

    Plateforme save(Plateforme plateforme);

    List<Plateforme> findAll();
}
