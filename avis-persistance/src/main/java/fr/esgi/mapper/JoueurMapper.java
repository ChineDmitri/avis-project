package fr.esgi.mapper;

import fr.esgi.entity.JoueurEntity;
import fr.esgi.model.Joueur;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface JoueurMapper {
//    Joueur dtoToDomain(JoueurEntity joueurDto);
//    JoueurEntity domainToDto(Joueur joueur);

    Joueur entityToDomain(JoueurEntity joueurEntity);
    JoueurEntity domainToEntity(Joueur joueur);
}
