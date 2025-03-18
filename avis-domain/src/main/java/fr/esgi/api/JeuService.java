package fr.esgi.api;


import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;

import java.io.InputStream;

public interface JeuService {
    Jeu ajouterJeu(Jeu jeu);

    Jeu recupererJeu(Long idJeu);

    CustomPagedResult<Jeu> recupererJeux(PaginationParams paginationParams);

    Jeu enregistrerJeu(Jeu jeu);

    boolean ajouterImage(Long id, InputStream image);
}
