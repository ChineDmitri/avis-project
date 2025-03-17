package fr.esgi.mapper;

import fr.esgi.entity.JeuEntity;
import fr.esgi.model.Jeu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EditeurMapper.class, PlateformeMapper.class}
)
public interface JeuMapper {
    //    Jeu dtoToDomain(JeuDto jeuDto);
    //    JeuDto domainToDto(Jeu jeu);

    Jeu entityToDomain(JeuEntity jeuEntity);

    JeuEntity domainToEntity(Jeu jeu);
}
