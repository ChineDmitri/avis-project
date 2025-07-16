package fr.esgi.api;


import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Add an image to a game using MultipartFile and return filename
     * @param id the game ID
     * @param multipartFile the uploaded file
     * @return the file path where the file was saved
     */
    String ajouterImage(Long id, MultipartFile multipartFile) throws
                                                              TechnicalException;
}
