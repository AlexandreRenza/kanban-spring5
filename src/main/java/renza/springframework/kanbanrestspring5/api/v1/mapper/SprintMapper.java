package renza.springframework.kanbanrestspring5.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import renza.springframework.kanbanrestspring5.api.v1.model.SprintDTO;
import renza.springframework.kanbanrestspring5.domain.Sprint;

@Mapper
public interface SprintMapper {

    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);

    @Mapping(target = "project_id", source="project.id")
    SprintDTO sprintToSprintDTO (Sprint sprint);

    Sprint sprintDTOToSprint(SprintDTO sprintDTO);

}
