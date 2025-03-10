package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
}
