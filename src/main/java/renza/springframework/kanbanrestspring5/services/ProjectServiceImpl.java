package renza.springframework.kanbanrestspring5.services;

import org.springframework.stereotype.Service;
import renza.springframework.kanbanrestspring5.api.v1.mapper.ProjectMapper;
import renza.springframework.kanbanrestspring5.api.v1.model.ProjectDTO;
import renza.springframework.kanbanrestspring5.domain.Project;
import renza.springframework.kanbanrestspring5.repositories.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository
                .findAll()
                .stream()
                .map(project -> {
                    ProjectDTO projectDTO = projectMapper.projectToProjectDTO(project);
                    return projectDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        return projectRepository
                .findById(id)
                .map(projectMapper::projectToProjectDTO)
                .orElseThrow(RuntimeException::new); // todo implementar no data found
    }

    @Override
    public ProjectDTO createNewProject(ProjectDTO projectDTO) {
        return saveAndReturnDTO(projectMapper.projectDTOToProject(projectDTO));
    }


    private ProjectDTO saveAndReturnDTO(Project project){

        Project savedProject = projectRepository.save(project);

        ProjectDTO returnDTO = projectMapper.projectToProjectDTO(savedProject);

        return returnDTO;
    }


    @Override
    public ProjectDTO saveProjectByDTO(Long id, ProjectDTO projectDTO) {

        Project project = projectMapper.projectDTOToProject(projectDTO);
        project.setId(id);

        ProjectDTO savedProjectDto = saveAndReturnDTO(project);

        return savedProjectDto;
    }

    @Override
    public void deleteProjectById(Long id) {

        projectRepository.deleteById(id);

    }
}
