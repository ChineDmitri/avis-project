package fr.esgi.fx.avis.service;

import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.dto.JeuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface JeuService {

    Page<Jeu> recupererJeux(Pageable pageable);

    Jeu ajouterJeu(JeuDto jeuDto);

    Jeu recupererJeu(Long idJeu);

    Jeu enregistrerJeu(Jeu jeu);

    boolean ajouterImage(Long id, MultipartFile image);
}
