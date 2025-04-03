package fr.esgi.mapper;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.model.Editeur;
import fr.esgi.model.Jeu;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EditeurMapper.class, PlateformeMapper.class}
)
public interface JeuMapper {
    //    Jeu dtoToDomain(JeuDto jeuDto);
    //    JeuDto domainToDto(Jeu jeu);

    @Mapping(target = "editeur", qualifiedByName = "mapEditeurAvoidingCycle")
    Jeu entityToDomain(JeuEntity jeuEntity,
                       @Context CycleAvoidingMappingContext context);

    JeuEntity domainToEntity(Jeu jeu, @Context CycleAvoidingMappingContext context);

    @Named("mapEditeurAvoidingCycle")
    default Editeur mapEditeurAvoidingCycle(EditeurEntity editeurEntity,
                                            @Context CycleAvoidingMappingContext context) {
        Editeur existing = context.getMappedInstance(editeurEntity, Editeur.class);
        if (existing != null) {
            return existing;
        }

        Editeur editeur = new Editeur(); // ou utilise un constructeur propre
        context.storeMappedInstance(editeurEntity, editeur);

        editeur.setId(editeurEntity.getId());
        editeur.setNom(editeurEntity.getNom());
        // on ignore les jeux ici ou on peut mapper manuellement si besoin
        return editeur;
    }
}
