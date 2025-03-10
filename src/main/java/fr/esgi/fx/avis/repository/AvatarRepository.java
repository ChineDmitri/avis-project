package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}