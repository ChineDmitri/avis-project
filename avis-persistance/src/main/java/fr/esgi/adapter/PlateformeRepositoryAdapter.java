package fr.esgi.adapter;

import fr.esgi.mapper.CycleAvoidingMappingContext;
import fr.esgi.mapper.PlateformeMapper;
import fr.esgi.model.Plateforme;
import fr.esgi.port.PlateformeRepository;
import fr.esgi.repository.PlateformeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PlateformeRepositoryAdapter implements PlateformeRepository {
    private PlateformeJpaRepository plateformeJpaRepository;
    private PlateformeMapper        plateformeMapper;

    @Override
    public List<Plateforme> findByNomContaining(String filtre) {
        return plateformeJpaRepository.findByNomContaining(filtre)
                                      .stream()
                                      .map(p -> plateformeMapper.entityToDomain(p, new CycleAvoidingMappingContext()))
                                      .collect(Collectors.toList());
    }

    @Override
    public Plateforme findByNom(String nom) {
        return plateformeMapper.entityToDomain(plateformeJpaRepository.findByNom(nom),
                                               new CycleAvoidingMappingContext());
    }

    @Override
    public void delete(Plateforme plateforme) {
        plateformeJpaRepository.delete(plateformeMapper.domainToEntity(plateforme,
                                                                       new CycleAvoidingMappingContext()));
    }

    @Override
    public Plateforme save(Plateforme plateforme) {
        return plateformeMapper.entityToDomain(plateformeJpaRepository.save(plateformeMapper.domainToEntity(plateforme,
                                                                                                            new CycleAvoidingMappingContext())),
                                               new CycleAvoidingMappingContext());
    }

    @Override
    public List<Plateforme> findAll() {
        return plateformeJpaRepository.findAll()
                                      .stream()
                                      .map(p -> plateformeMapper.entityToDomain(p, new CycleAvoidingMappingContext()))
                                      .collect(Collectors.toList());
    }
}
