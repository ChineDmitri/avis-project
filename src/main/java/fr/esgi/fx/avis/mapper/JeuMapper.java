package fr.esgi.fx.avis.mapper;

import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.dto.JeuDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JeuMapper {
    Jeu toEntity(JeuDto jeuDto);

    JeuDto toDto(Jeu jeu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jeu partialUpdate(JeuDto jeuDto, @MappingTarget Jeu jeu);
}