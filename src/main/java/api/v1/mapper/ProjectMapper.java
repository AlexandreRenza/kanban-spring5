package api.v1.mapper;

import api.v1.model.ProjectDTO;
import domain.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "id", target = "id")
    ProjectDTO projectToProjectDTO(Project project);


}
