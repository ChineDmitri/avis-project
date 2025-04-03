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

    @Mapping(target = "jeux", ignore = true)
    Editeur entityToDomain(EditeurEntity editeurEntity,
                           @Context CycleAvoidingMappingContext context);

    @Mapping(target = "jeux", ignore = true)
    EditeurEntity domainToEntity(Editeur editeur,
                                 @Context CycleAvoidingMappingContext context);

    //    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //    Editeur partialUpdate(EditeurDto editeurDto, @MappingTarget Editeur editeur);
}
