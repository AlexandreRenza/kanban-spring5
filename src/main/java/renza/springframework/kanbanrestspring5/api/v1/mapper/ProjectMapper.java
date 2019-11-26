package renza.springframework.kanbanrestspring5.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import renza.springframework.kanbanrestspring5.api.v1.model.ProjectDTO;
import renza.springframework.kanbanrestspring5.domain.Project;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO projectToProjectDTO (Project project);

    Project projectDTOToProject (ProjectDTO projectDTO);

}
