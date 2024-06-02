package dev.luanfernandes.domain.mapper;

import dev.luanfernandes.domain.dto.MovieCSV;
import dev.luanfernandes.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "awardYear", source = "year")
    Movie map(MovieCSV value);
}
