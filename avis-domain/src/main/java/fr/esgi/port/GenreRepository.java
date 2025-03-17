package fr.esgi.port;

import fr.esgi.model.Genre;

import java.util.List;

public interface GenreRepository {
    Genre findByNom(String nom);

    List<Genre> findByNomContaining(String nom);
}
