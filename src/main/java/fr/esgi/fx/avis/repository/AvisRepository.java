package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {
}