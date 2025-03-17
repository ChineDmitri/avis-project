package fr.esgi.service;


import fr.esgi.model.Jeu;

import java.io.File;
import java.io.FileInputStream;

public interface JeuService {
    Jeu ajouterJeu(Jeu jeu);

    Jeu recupererJeu(Long idJeu);

    Jeu enregistrerJeu(Jeu jeu);

    boolean ajouterImage(Long id, FileInputStream image);
}
