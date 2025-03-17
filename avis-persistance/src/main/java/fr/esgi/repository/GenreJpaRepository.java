package fr.esgi.repository;

import fr.esgi.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity findByNom(String nom);

    List<GenreEntity> findByNomContaining(String nom);

}
