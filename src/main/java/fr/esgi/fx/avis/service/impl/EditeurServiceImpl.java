package fr.esgi.fx.avis.service.impl;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.dto.EditeurDto;
import fr.esgi.fx.avis.exception.EditeurDejaPresentException;
import fr.esgi.fx.avis.exception.EditeurInexistantException;
import fr.esgi.fx.avis.repository.EditeurRepository;
import fr.esgi.fx.avis.service.EditeurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditeurServiceImpl implements EditeurService {

    // Dépendance
    //@Autowired
    private EditeurRepository editeurRepository;

    // Ce constructeur contient en paramètre tout ce que Spring doit instancier
    // et injecter dans le service
    public EditeurServiceImpl(EditeurRepository editeurRepository) {
        this.editeurRepository = editeurRepository;
    }

    //@Override
    public Editeur ajouterEditeur(Editeur editeur) {
        /*
        if (recupererEditeurParNom(editeur.getNom())!=null) {
            throw new EditeurDejaPresentException("Cet éditeur est déjà présent !");
        }
        return editeurRepository.save(editeur);
        */
        editeurRepository.findByNomIgnoreCase(editeur.getNom()).ifPresent(
                e -> { throw new EditeurDejaPresentException(String.format("L'éditeur %s est déjà présent", editeur.getNom()));});
        return editeurRepository.save(editeur);
    }

    //@Override
    public Editeur ajouterEditeur(EditeurDto editeurDto) {
        // On hydrate un nouvel objet métier en utilisant les données portées par l'objet editeurDto
        Editeur editeur = new Editeur();
        editeur.setNom(editeurDto.getNom());
        ajouterEditeur(editeur);
        return editeur;
    }

    @Override
    public List<Editeur> recupererEditeurs() {
        return editeurRepository.findAll();
    }

    @Override
    public List<Editeur> recupererEditeursParNomContenant(String nom) {
        //return editeurRepository.findAll().stream().filter(e -> e.getNom().toLowerCase().contains(nom.toLowerCase())).toList();
        return editeurRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public Editeur recupererEditeurParNom(String nom) {
        return editeurRepository.findByNom(nom);
    }

    @Override
    public Editeur recupererEditeur(Long id) {
        //return editeurRepository.findById(id).orElseThrow(() -> new EditeurInexistantException("Cet éditeur n'existe pas"));
        return editeurRepository.findById(id).orElseThrow(EditeurInexistantException::new);
    }

    @Override
    public void supprimerEditeur(Long id) {
        if (recupererEditeur(id)==null) {
            throw new EditeurInexistantException("Cet éditeur n'existe pas !");
        }
        editeurRepository.deleteById(id);
    }
}
