package fr.esgi.mapper;

import fr.esgi.entity.PlateformeEntity;
import fr.esgi.model.Plateforme;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PlateformeMapper {
//    Plateforme dtoToDomain(PlateformeDto plateformeDto);
//    PlateformeDto domainToDto(Plateforme plateforme);

    Plateforme entityToDomain(PlateformeEntity plateformeEntity);
    PlateformeEntity domainToEntity(Plateforme plateforme);
}
