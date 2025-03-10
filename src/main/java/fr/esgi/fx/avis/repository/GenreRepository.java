package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByNom(String nom);

    List<Genre> findByNomContaining(String nom);

}