package fr.esgi.repository;

import fr.esgi.entity.ModerateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModerateurJpaRepository extends JpaRepository<ModerateurEntity, Long> {
}
