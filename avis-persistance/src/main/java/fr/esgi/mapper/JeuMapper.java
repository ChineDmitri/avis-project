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
        if (editeurEntity == null) {
            return null;
        }

        Editeur existing = context.getMappedInstance(editeurEntity, Editeur.class);
        if (existing != null) {
            return existing;
        }

        Editeur editeur = new Editeur(); // or use a proper constructor
        context.storeMappedInstance(editeurEntity, editeur);

        editeur.setId(editeurEntity.getId());
        editeur.setNom(editeurEntity.getNom());
        // ignore games here or map manually if needed
        return editeur;
    }
}
