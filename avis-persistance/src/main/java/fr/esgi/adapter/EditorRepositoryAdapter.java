package fr.esgi.adapter;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.mapper.CycleAvoidingMappingContext;
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
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return editeurJpaRepository.findEditorsWithoutGames()
                                   .stream()
                                   .map(e -> jeuMapper.entityToDomain(
                                                e,
                                                new CycleAvoidingMappingContext()
                                        )
                                   )
                                   .collect(Collectors.toList());
    }

    @Override
    public List<Editeur> findByNomContainingIgnoreCase(String nom) {
        return editeurJpaRepository.findByNomContainingIgnoreCase(nom)
                                   .stream()
                                   .map(e -> editeurMapper.entityToDomain(
                                                e,
                                                new CycleAvoidingMappingContext()
                                        )
                                   )
                                   .collect(Collectors.toList());
    }

    @Override
    public Editeur findByNom(String nom) {
        EditeurEntity entity = editeurJpaRepository.findByNom(nom);
        return entity != null ? editeurMapper.entityToDomain(entity, new CycleAvoidingMappingContext()) : null;
    }

    @Override
    public Optional<Editeur> findByNomIgnoreCase(String nom) {
        return editeurJpaRepository.findByNomIgnoreCase(nom)
                                   .map(e -> editeurMapper.entityToDomain(
                                                e,
                                                new CycleAvoidingMappingContext()
                                        )
                                   );
    }

    @Override
    public Editeur save(Editeur editeur) {
        EditeurEntity entity      = editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext());
        EditeurEntity savedEntity = editeurJpaRepository.save(entity);
        return editeurMapper.entityToDomain(savedEntity, new CycleAvoidingMappingContext());
    }

    @Override
    public List<Editeur> findAll() {
        return editeurJpaRepository.findAll()
                                   .stream()
                                   .map(editeurEntity -> editeurMapper.entityToDomain(
                                                editeurEntity,
                                                new CycleAvoidingMappingContext()
                                        )
                                   )
                                   .collect(Collectors.toList());
    }

    @Override
    public Optional<Editeur> findById(Long id) {
        return editeurJpaRepository.findById(id)
                                   .map(editeurEntity -> editeurMapper.entityToDomain(
                                                editeurEntity,
                                                new CycleAvoidingMappingContext()
                                        )
                                   );
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
