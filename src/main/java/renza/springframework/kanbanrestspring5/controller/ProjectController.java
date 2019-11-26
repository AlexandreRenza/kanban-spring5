package renza.springframework.kanbanrestspring5.controller;

import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import renza.springframework.kanbanrestspring5.api.v1.model.ProjectDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.ProjectListDTO;
import renza.springframework.kanbanrestspring5.services.ProjectService;

@RestController
@RequestMapping(ProjectController.BASE_URL)
@CrossOrigin(origins = "${originUrl}", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ProjectController {


    public static final String BASE_URL = "api/v1/projects";

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProjectListDTO getListOfProjects(){
        return new ProjectListDTO(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO createNewProject(@RequestBody ProjectDTO projectDTO){
        return projectService.createNewProject(projectDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO){
        return projectService.saveProjectByDTO(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long id){

        projectService.deleteProjectById(id);

    }

}
