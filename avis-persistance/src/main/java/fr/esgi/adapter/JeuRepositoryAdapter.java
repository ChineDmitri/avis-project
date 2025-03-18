package fr.esgi.adapter;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.entity.GenreEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.mapper.EditeurMapper;
import fr.esgi.mapper.JeuMapper;
import fr.esgi.mapper.PlateformeMapper;
import fr.esgi.model.Editeur;
import fr.esgi.model.Genre;
import fr.esgi.model.Jeu;
import fr.esgi.model.Plateforme;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.port.JeuRepository;
import fr.esgi.repository.JeuJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JeuRepositoryAdapter implements JeuRepository {
    private JeuJpaRepository jeuJpaRepository;
    private JeuMapper        jeuMapper;
    private EditeurMapper    editeurMapper;
    private PlateformeMapper plateformeMapper;
    private PageAdapter      pageAdapter;

    @Override
    public Jeu findById(Long id) {
        return jeuJpaRepository.findById(id)
                               .map(jeuMapper::entityToDomain)
                               .orElse(null);
    }

    @Override
    public List<Jeu> findAll() {
        return jeuJpaRepository.findAll()
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public CustomPagedResult<Jeu> findAll(PaginationParams paginationParams) {
        //        Page<JeuEntity> page = jeuJpaRepository.findAll(
        //                this.pageAdapter.toSpringPageable(paginationParams)
        //        );
        return pageAdapter.fromSpringPage(
                jeuJpaRepository.findAll(
                        pageAdapter.toSpringPageable(paginationParams)
                ),
                jeuMapper::entityToDomain);
    }


    @Override
    public Jeu findFirstByNom(String nom) {
        JeuEntity entity = jeuJpaRepository.findFirstByNom(nom);
        return entity != null ? jeuMapper.entityToDomain(entity) : null;
    }

    @Override
    public List<Jeu> findByEditeur(Editeur editeur) {
        List<JeuEntity> entities = jeuJpaRepository.findByEditeur(editeurMapper.domainToEntity(editeur));
        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByEditeurNom(String nom) {
        List<JeuEntity> entities = jeuJpaRepository.findByEditeurNom(nom);
        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByEditeurAndGenre(Editeur editeur, Genre genre) {
        List<JeuEntity> entities = jeuJpaRepository.findByEditeurAndGenre(editeurMapper.domainToEntity(editeur),
                                                                          GenreEntity.builder()
                                                                                     .id(genre.getId())
                                                                                     .build());
        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findTop5ByEditeurOrderByDateDeSortieDesc(Editeur editeur) {
        List<JeuEntity> entities = jeuJpaRepository.findTop5ByEditeurOrderByDateDeSortieDesc(editeurMapper.domainToEntity(editeur));

        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByEditeurAndGenreAndClassificationNom(Editeur editeur, Genre genre, String nom) {
        List<JeuEntity> entities = jeuJpaRepository.findByEditeurAndGenreAndClassificationNom(editeurMapper.domainToEntity(editeur),
                                                                                              GenreEntity.builder()
                                                                                                         .id(genre.getId())
                                                                                                         .build(),
                                                                                              nom);

        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByGenre(Genre genre) {
        GenreEntity genreEntity = GenreEntity.builder()
                                             .id(genre.getId())
                                             .build();
        List<JeuEntity> entities = jeuJpaRepository.findByGenre(genreEntity);

        return entities.stream()
                       .map(jeuMapper::entityToDomain)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByGenreNom(String nom) {
        return jeuJpaRepository.findByGenreNom(nom)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByNomLike(String nom) {
        return jeuJpaRepository.findByNomLike(nom)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByNomLikeAndDateDeSortieBetween(String nom, LocalDate dateDebut, LocalDate dateFin) {
        return jeuJpaRepository.findByNomLikeAndDateDeSortieBetween(nom, dateDebut, dateFin)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByEditeurAndNomLikeAndDateDeSortieBetween(Editeur editeur, String nom, LocalDate dateDebut, LocalDate dateFin) {
        return jeuJpaRepository.findByEditeurAndNomLikeAndDateDeSortieBetween(editeurMapper.domainToEntity(editeur),
                                                                              nom,
                                                                              dateDebut,
                                                                              dateFin)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findAllByPlateformesContaining(Plateforme plateforme) {
        return jeuJpaRepository.findAllByPlateformesContaining(plateformeMapper.domainToEntity(plateforme))
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByPlateformesNom(String nom) {
        return jeuJpaRepository.findByPlateformesNom(nom)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public Optional<Jeu> findByNom(String nom) {
        return jeuJpaRepository.findByNom(nom)
                               .map(jeuMapper::entityToDomain);
    }

    @Override
    public List<Jeu> findByNomEndingWith(String filtre) {
        return jeuJpaRepository.findByNomEndingWith(filtre)
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNom(String nom) {
        return jeuJpaRepository.existsByNom(nom);
    }

    @Override
    public long countByEditeur(Editeur editeur) {
        return jeuJpaRepository.countByEditeur(editeurMapper.domainToEntity(editeur));
    }

    @Override
    public Jeu save(Jeu jeu) {
        JeuEntity je = jeuJpaRepository.save(jeuMapper.domainToEntity(jeu));
        return jeuMapper.entityToDomain(je);
    }

    @Override
    public long deleteByEditeur(Editeur editeur) {
        return jeuJpaRepository.deleteByEditeur(editeurMapper.domainToEntity(editeur));
    }

    @Override
    public List<Jeu> findByPlateformes(Plateforme plateforme) {
        return jeuJpaRepository.findByPlateformes(plateformeMapper.domainToEntity(plateforme))
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Jeu> findByDateDeSortieBetweenAndGenreAndImageNotNull(LocalDate dateDeSortieStart, LocalDate dateDeSortieEnd, Genre genre) {
        return jeuJpaRepository.findByDateDeSortieBetweenAndGenreAndImageNotNull(dateDeSortieStart,
                                                                                 dateDeSortieEnd,
                                                                                 GenreEntity.builder()
                                                                                            .id(genre.getId())
                                                                                            .build())
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }

    @Override
    public Optional<Jeu> findByNomIgnoreCase(String nom) {
        return jeuJpaRepository.findByNomIgnoreCase(nom)
                               .map(jeuMapper::entityToDomain);
    }

    @Override
    public List<Jeu> findGamesRandomlySorted() {
        return jeuJpaRepository.findGamesRandomlySorted()
                               .stream()
                               .map(jeuMapper::entityToDomain)
                               .collect(Collectors.toList());
    }
}
