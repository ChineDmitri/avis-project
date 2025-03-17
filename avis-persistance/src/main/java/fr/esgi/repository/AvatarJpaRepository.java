package fr.esgi.repository;

import fr.esgi.entity.AvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarJpaRepository extends JpaRepository<AvatarEntity, Long> {
}
