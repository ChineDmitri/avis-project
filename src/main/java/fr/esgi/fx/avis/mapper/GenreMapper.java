package fr.esgi.fx.avis.mapper;

import fr.esgi.fx.avis.business.Genre;
import fr.esgi.fx.avis.dto.GenreDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    Genre toEntity(GenreDto genreDto);

    GenreDto toDto(Genre genre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Genre partialUpdate(GenreDto genreDto, @MappingTarget Genre genre);
}