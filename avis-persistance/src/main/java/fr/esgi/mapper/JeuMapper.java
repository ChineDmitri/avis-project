package fr.esgi.mapper;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.entity.GenreEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.model.Editeur;
import fr.esgi.model.Genre;
import fr.esgi.model.Jeu;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EditeurMapper.class, PlateformeMapper.class}
)
public interface JeuMapper {

    @Mapping(target = "editeur", qualifiedByName = "mapEditeurAvoidingCycle")
    @Mapping(target = "genre", qualifiedByName = "mapGenreAvoidingCycle")
    Jeu entityToDomain(JeuEntity jeuEntity,
                       @Context CycleAvoidingMappingContext context);

    JeuEntity domainToEntity(Jeu jeu, @Context CycleAvoidingMappingContext context);

    // Méthodes pour les listes (nécessaires pour la pagination)
    List<Jeu> entityListToDomainList(List<JeuEntity> jeuEntities, 
                                     @Context CycleAvoidingMappingContext context);

    List<JeuEntity> domainListToEntityList(List<Jeu> jeux, 
                                          @Context CycleAvoidingMappingContext context);

    @Named("mapEditeurAvoidingCycle")
    default Editeur mapEditeurAvoidingCycle(EditeurEntity editeurEntity,
                                            @Context CycleAvoidingMappingContext context) {
        if (editeurEntity == null) {
            return null;
        }

        Editeur existing = context.getMappedInstance(editeurEntity, Editeur.class);
        if (existing != null) {
            return existing;
        }

        Editeur editeur = Editeur.builder()
                .id(editeurEntity.getId())
                .nom(editeurEntity.getNom())
                .build();
        context.storeMappedInstance(editeurEntity, editeur);

        return editeur;
    }

    @Named("mapGenreAvoidingCycle")
    default Genre mapGenreAvoidingCycle(GenreEntity genreEntity,
                                        @Context CycleAvoidingMappingContext context) {
        if (genreEntity == null) {
            return null;
        }

        Genre existing = context.getMappedInstance(genreEntity, Genre.class);
        if (existing != null) {
            return existing;
        }

        Genre genre = Genre.builder()
                .id(genreEntity.getId())
                .nom(genreEntity.getNom())
                .build();
        context.storeMappedInstance(genreEntity, genre);

        return genre;
    }
}
