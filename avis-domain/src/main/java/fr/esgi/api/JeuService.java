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

    /**
     * Add an image to a game and return filename
     * if exception occurs, return `StringUtils.EMPTY`
     * @param id
     * @param is
     * @return
     */
    String ajouterImage(Long id, InputStream is);
}
