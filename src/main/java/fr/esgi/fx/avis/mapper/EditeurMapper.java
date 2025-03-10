package fr.esgi.fx.avis.mapper;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.dto.EditeurDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EditeurMapper {
    Editeur toEntity(EditeurDto editeurDto);

    EditeurDto toDto(Editeur editeur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Editeur partialUpdate(EditeurDto editeurDto, @MappingTarget Editeur editeur);
}