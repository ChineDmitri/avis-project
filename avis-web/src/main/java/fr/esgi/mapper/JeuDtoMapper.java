package fr.esgi.mapper;

import fr.esgi.dto.JeuDto;
import fr.esgi.model.Jeu;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JeuDtoMapper {
    Jeu toModel(JeuDto jeuDto);

    JeuDto toDto(Jeu jeu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jeu partialUpdate(JeuDto jeuDto, @MappingTarget Jeu jeu);
}
