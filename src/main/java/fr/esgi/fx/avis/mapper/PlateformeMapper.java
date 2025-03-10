package fr.esgi.fx.avis.mapper;

import fr.esgi.fx.avis.business.Plateforme;
import fr.esgi.fx.avis.dto.PlateformeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlateformeMapper {
    Plateforme toEntity(PlateformeDto plateformeDto);

    //@Mapping(target="dateDeSortie", source = "dateDeSortie", expression = "")
    PlateformeDto toDto(Plateforme plateforme);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Plateforme partialUpdate(PlateformeDto plateformeDto, @MappingTarget Plateforme plateforme);
}