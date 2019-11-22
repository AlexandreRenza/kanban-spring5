package service;

import api.v1.model.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long id);

    ProjectDTO createNewProject(ProjectDTO projectDTO);

    ProjectDTO saveProjectByDTO(Long id, ProjectDTO projectDTO);

    void deleteProjectById(Long id);


}
