package fr.esgi.adapter;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.mapper.EditeurMapper;
import fr.esgi.mapper.JeuMapper;
import fr.esgi.model.Editeur;
import fr.esgi.model.Jeu;
import fr.esgi.port.EditeurRepository;
import fr.esgi.repository.EditeurJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EditorRepositoryAdapter implements EditeurRepository {
    private EditeurJpaRepository editeurJpaRepository;
    private EditeurMapper        editeurMapper;
    private JeuMapper            jeuMapper;




    @Override
    public List<Jeu> findEditorsWithoutGames() {
        return editeurJpaRepository.findEditorsWithoutGames()
                                   .stream()
                                   .map(jeuMapper::entityToDomain)
                                   .collect(Collectors.toList());
    }

    @Override
    public List<Editeur> findByNomContainingIgnoreCase(String nom) {
        return editeurJpaRepository.findByNomContainingIgnoreCase(nom)
                                   .stream()
                                   .map(editeurMapper::entityToDomain)
                                   .collect(Collectors.toList());
    }

    @Override
    public Editeur findByNom(String nom) {
        EditeurEntity entity = editeurJpaRepository.findByNom(nom);
        return entity != null ? editeurMapper.entityToDomain(entity) : null;
    }

    @Override
    public Optional<Editeur> findByNomIgnoreCase(String nom) {
        return editeurJpaRepository.findByNomIgnoreCase(nom)
                                   .map(editeurMapper::entityToDomain);
    }

    @Override
    public Editeur save(Editeur editeur) {
        EditeurEntity entity      = editeurMapper.domainToEntity(editeur);
        EditeurEntity savedEntity = editeurJpaRepository.save(entity);
        return editeurMapper.entityToDomain(savedEntity);
    }

    @Override
    public List<Editeur> findAll() {
        return editeurJpaRepository.findAll()
                                   .stream()
                                   .map(editeurMapper::entityToDomain)
                                   .collect(Collectors.toList());
    }

    @Override
    public Optional<Editeur> findById(Long id) {
        return editeurJpaRepository.findById(id)
                                   .map(editeurMapper::entityToDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return editeurJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        editeurJpaRepository.deleteById(id);
    }
}
