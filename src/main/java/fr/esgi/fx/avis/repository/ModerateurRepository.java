package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Moderateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModerateurRepository extends JpaRepository<Moderateur, Long> {
}