package fr.esgi.repository;

import fr.esgi.entity.PlateformeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface PlateformeJpaRepository extends JpaRepository<PlateformeEntity, Long> {

    @Query("""
        FROM PlateformeEntity
        WHERE lower(nom) LIKE CONCAT('%', lower(:filtre), '%')
        """)
    List<PlateformeEntity> findByNomContaining(@Param("filtre") String filtre);

    PlateformeEntity findByNom(String nom);

    @RestResource(exported = false)
    void delete(PlateformeEntity plateforme);

    @RestResource(exported = false)
    PlateformeEntity save(PlateformeEntity plateforme);
}
