package fr.esgi.fx.avis.service;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.dto.EditeurDto;

import java.util.List;

public interface EditeurService {

    Editeur ajouterEditeur(Editeur editeur);

    Editeur ajouterEditeur(EditeurDto editeurDto);

    List<Editeur> recupererEditeurs();

    Editeur recupererEditeurParNom(String nom);

    List<Editeur> recupererEditeursParNomContenant(String nom);

    Editeur recupererEditeur(Long id);

    void supprimerEditeur(Long id);

}
