package fr.esgi.fx.avis.repository;

import fr.esgi.fx.avis.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}