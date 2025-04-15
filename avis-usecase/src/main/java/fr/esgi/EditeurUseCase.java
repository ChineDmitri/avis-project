package fr.esgi;

import fr.esgi.api.EditeurService;
import fr.esgi.model.Editeur;
import fr.esgi.port.EditeurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EditeurUseCase implements EditeurService {

    @Autowired
    private EditeurRepository editeurRepository;

    @Override
    public Editeur ajouterEditeur(Editeur editeur) {
        return editeurRepository.save(editeur);
    }

    @Override
    public List<Editeur> recupererEditeurs() {
        return editeurRepository.findAll();
    }

    @Override
    public Editeur recupererEditeurParNom(String nom) {
        return editeurRepository.findByNom(nom);
    }

    @Override
    public List<Editeur> recupererEditeursParNomContenant(String nom) {
        return editeurRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public Editeur recupererEditeur(Long id) {
        return editeurRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerEditeur(Long id) {
        editeurRepository.deleteById(id);
    }
}
