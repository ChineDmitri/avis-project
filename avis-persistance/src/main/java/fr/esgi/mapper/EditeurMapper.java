package fr.esgi.mapper;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.model.Editeur;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface EditeurMapper {
    //    Editeur dtoToDomain(EditeurEntity editeurDto);
    //    EditeurDto domainToDto(Editeur editeur);

        Editeur entityToDomain(EditeurEntity editeurEntity);
        EditeurEntity domainToEntity(Editeur editeur);

    //    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //    Editeur partialUpdate(EditeurDto editeurDto, @MappingTarget Editeur editeur);
}
