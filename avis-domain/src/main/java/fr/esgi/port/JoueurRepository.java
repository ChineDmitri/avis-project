package fr.esgi.port;

import fr.esgi.model.Joueur;

import java.time.LocalDate;
import java.util.List;

public interface JoueurRepository<T> {
    List<Joueur> findJoueursCelebrantLeurAnniversaireAujourdhui();

    List<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance);

    List<Joueur> findTop10ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    List<Joueur> findTop1ByDateDeNaissanceOrderByPseudo(LocalDate dateDeNaissance);

    long countByDateDeNaissanceBetween(LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd);

    List<T> findNbJoueursParAnnee();

    List<Joueur> findByNbAvisDesc();
}
