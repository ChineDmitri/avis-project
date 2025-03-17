package fr.esgi.repository;

import fr.esgi.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurJpaRepository extends JpaRepository<UtilisateurEntity, Long> {
}
