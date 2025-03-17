package fr.esgi.repository;

import fr.esgi.entity.AvisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisJpaRepository extends JpaRepository<AvisEntity, Long> {
}
