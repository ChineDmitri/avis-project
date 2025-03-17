package fr.esgi.adapter;

import fr.esgi.entity.AvisEntity;
import fr.esgi.mapper.AvisMapper;
import fr.esgi.model.Avis;
import fr.esgi.port.AvisRepository;
import fr.esgi.repository.AvisJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AvisRepositoryAdapter implements AvisRepository {

    private AvisJpaRepository avisJpaRepository;
    private AvisMapper        avisMapper;


    @Override
    public Avis save(Avis avis) {
        AvisEntity entity      = avisMapper.domainToEntity(avis);
        AvisEntity savedEntity = avisJpaRepository.save(entity);
        return avisMapper.entityToDomain(savedEntity);
    }

    @Override
    public List<Avis> findAll() {
        return avisJpaRepository.findAll().stream()
                                .map(avisMapper::entityToDomain)
                                .collect(Collectors.toList());
    }

    @Override
    public Optional<Avis> findById(Long id) {
        return avisJpaRepository.findById(id)
                                .map(avisMapper::entityToDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return avisJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        avisJpaRepository.deleteById(id);
    }
}
