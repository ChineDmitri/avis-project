package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Plateforme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface PlateformeRepository extends JpaRepository<Plateforme, Long> {

    @Query("""
        FROM Plateforme
        WHERE lower(nom) LIKE CONCAT('%', lower(:filtre), '%')
        """)
    List<Plateforme> findByNomContaining(@Param("filtre") String filtre);

    Plateforme findByNom(String nom);

    @RestResource(exported = false)
    void delete(Plateforme plateforme);

    @RestResource(exported = false)
    Plateforme save(Plateforme plateforme);
}