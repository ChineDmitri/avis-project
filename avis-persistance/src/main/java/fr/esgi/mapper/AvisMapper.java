package fr.esgi.mapper;

import fr.esgi.entity.AvisEntity;
import fr.esgi.model.Avis;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {JoueurMapper.class, JeuMapper.class}
)
public interface AvisMapper {
//    Avis dtoToDomain(AvisEntity avisDto);
    //    AvisEntity domainToDto(Avis avis);

    Avis entityToDomain(AvisEntity avisEntity);
    AvisEntity domainToEntity(Avis avis);
}
