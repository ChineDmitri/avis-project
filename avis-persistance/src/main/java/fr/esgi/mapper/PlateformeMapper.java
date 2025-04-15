package fr.esgi.mapper;

import fr.esgi.entity.PlateformeEntity;
import fr.esgi.model.Plateforme;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PlateformeMapper {

    @Mapping(target = "jeux", ignore = true) // éviter la récursivité infinie
    Plateforme entityToDomain(PlateformeEntity plateformeEntity, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "jeux", ignore = true)
    PlateformeEntity domainToEntity(Plateforme plateforme, @Context CycleAvoidingMappingContext context);
}
