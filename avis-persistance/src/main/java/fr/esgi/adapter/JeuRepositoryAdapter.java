package fr.esgi.adapter;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.entity.GenreEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.mapper.CycleAvoidingMappingContext;
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
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JeuRepositoryAdapter implements JeuRepository {
    private JeuJpaRepository jeuJpaRepository;
    private JeuMapper        jeuMapper;
    private EditeurMapper    editeurMapper;
    private PlateformeMapper plateformeMapper;
    private PageAdapter      pageAdapter;

    @Override
    public Jeu findById(final Long id) {
        return jeuJpaRepository.findById(id)
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .orElse(null);
    }

    @Override
    public List<Jeu> findAll() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return jeuMapper.entityListToDomainList(jeuJpaRepository.findAll(), context);
    }

    @Override
    public CustomPagedResult<Jeu> findAll(final PaginationParams paginationParams) {
        System.out.println("jeuMapper = " + jeuMapper);

        return pageAdapter.fromSpringPage(
                jeuJpaRepository.findAll(
                        pageAdapter.toSpringPageable(paginationParams)
                ),
                entity -> {
                    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
                    return jeuMapper.entityToDomain(entity, context);
                }
        );
    }


    @Override
    public Jeu findFirstByNom(final String nom) {
        final JeuEntity entity = jeuJpaRepository.findFirstByNom(nom);
        return entity != null ? jeuMapper.entityToDomain(entity, new CycleAvoidingMappingContext()) : null;
    }

    @Override
    public List<Jeu> findByEditeur(final Editeur editeur) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        final List<JeuEntity> entities = jeuJpaRepository.findByEditeur(editeurMapper.domainToEntity(editeur, context));
        return jeuMapper.entityListToDomainList(entities, context);
    }

    @Override
    public List<Jeu> findByEditeurNom(final String nom) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        final List<JeuEntity> entities = jeuJpaRepository.findByEditeurNom(nom);
        return jeuMapper.entityListToDomainList(entities, context);
    }

    @Override
    public List<Jeu> findByEditeurAndGenre(final Editeur editeur, final Genre genre) {
        final List<JeuEntity> entities = jeuJpaRepository.findByEditeurAndGenre(editeurMapper.domainToEntity(
                                                                                  editeur,
                                                                                  new CycleAvoidingMappingContext()
                                                                          ),
                                                                          GenreEntity.builder()
                                                                                     .id(genre.getId())
                                                                                     .nom(genre.getNom())
                                                                                     .build());
        return entities.stream()
                       .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                       .toList();
    }

    @Override
    public List<Jeu> findTop5ByEditeurOrderByDateDeSortieDesc(final Editeur editeur) {
        final List<JeuEntity> entities = jeuJpaRepository.findTop5ByEditeurOrderByDateDeSortieDesc(editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext()));

        return entities.stream()
                       .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                       .toList();
    }

    @Override
    public List<Jeu> findByEditeurAndGenreAndClassificationNom(final Editeur editeur, final Genre genre, final String nom) {
        final List<JeuEntity> entities = jeuJpaRepository.findByEditeurAndGenreAndClassificationNom(editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext()),
                                                                                              GenreEntity.builder()
                                                                                                         .id(genre.getId())
                                                                                                         .build(),
                                                                                              nom);

        return entities.stream()
                       .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                       .toList();
    }

    @Override
    public List<Jeu> findByGenre(final Genre genre) {
        final GenreEntity genreEntity = GenreEntity.builder()
                                             .id(genre.getId())
                                             .nom(genre.getNom())
                                             .build();
        final List<JeuEntity> entities = jeuJpaRepository.findByGenre(genreEntity);

        return entities.stream()
                       .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                       .toList();
    }

    @Override
    public List<Jeu> findByGenreNom(final String nom) {
        return jeuJpaRepository.findByGenreNom(nom)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findByNomLike(final String nom) {
        return jeuJpaRepository.findByNomLike(nom)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findByNomLikeAndDateDeSortieBetween(final String nom, final LocalDate dateDebut, final LocalDate dateFin) {
        return jeuJpaRepository.findByNomLikeAndDateDeSortieBetween(nom, dateDebut, dateFin)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findByEditeurAndNomLikeAndDateDeSortieBetween(final Editeur editeur, final String nom, final LocalDate dateDebut, final LocalDate dateFin) {
        return jeuJpaRepository.findByEditeurAndNomLikeAndDateDeSortieBetween(editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext()),
                                                                              nom,
                                                                              dateDebut,
                                                                              dateFin)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findAllByPlateformesContaining(final Plateforme plateforme) {
        return jeuJpaRepository.findAllByPlateformesContaining(plateformeMapper.domainToEntity(plateforme,
                                                                                               new CycleAvoidingMappingContext()))
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findByPlateformesNom(final String nom) {
        return jeuJpaRepository.findByPlateformesNom(nom)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public Optional<Jeu> findByNom(final String nom) {
        return jeuJpaRepository.findByNom(nom)
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public List<Jeu> findByNomEndingWith(final String filtre) {
        return jeuJpaRepository.findByNomEndingWith(filtre)
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public boolean existsByNom(final String nom) {
        return jeuJpaRepository.existsByNom(nom);
    }

    @Override
    public long countByEditeur(final Editeur editeur) {
        return jeuJpaRepository.countByEditeur(editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext()));
    }

    @Override
    public Jeu save(final Jeu jeu) {
        final JeuEntity je = jeuJpaRepository.save(jeuMapper.domainToEntity(jeu, new CycleAvoidingMappingContext()));
        return jeuMapper.entityToDomain(je, new CycleAvoidingMappingContext());
    }

    @Override
    public long deleteByEditeur(final Editeur editeur) {
        return jeuJpaRepository.deleteByEditeur(editeurMapper.domainToEntity(editeur, new CycleAvoidingMappingContext()));
    }

    @Override
    public List<Jeu> findByPlateformes(final Plateforme plateforme) {
        return jeuJpaRepository.findByPlateformes(plateformeMapper.domainToEntity(plateforme,
                                                                                  new CycleAvoidingMappingContext()))
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public List<Jeu> findByDateDeSortieBetweenAndGenreAndImageNotNull(final LocalDate dateDeSortieStart, final LocalDate dateDeSortieEnd, final Genre genre) {
        return jeuJpaRepository.findByDateDeSortieBetweenAndGenreAndImageNotNull(dateDeSortieStart,
                                                                                 dateDeSortieEnd,
                                                                                 GenreEntity.builder()
                                                                                            .id(genre.getId())
                                                                                            .build())
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }

    @Override
    public Optional<Jeu> findByNomIgnoreCase(final String nom) {
        return jeuJpaRepository.findByNomIgnoreCase(nom)
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public List<Jeu> findGamesRandomlySorted() {
        return jeuJpaRepository.findGamesRandomlySorted()
                               .stream()
                               .map(jeuEntity -> jeuMapper.entityToDomain(jeuEntity, new CycleAvoidingMappingContext()))
                               .toList();
    }
}
