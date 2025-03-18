package fr.esgi.api;


import fr.esgi.model.Editeur;

import java.util.List;

public interface EditeurService {

    Editeur ajouterEditeur(Editeur editeur);

    List<Editeur> recupererEditeurs();

    Editeur recupererEditeurParNom(String nom);

    List<Editeur> recupererEditeursParNomContenant(String nom);

    Editeur recupererEditeur(Long id);

    void supprimerEditeur(Long id);

}
