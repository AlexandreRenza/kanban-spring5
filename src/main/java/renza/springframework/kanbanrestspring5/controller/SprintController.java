package renza.springframework.kanbanrestspring5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import renza.springframework.kanbanrestspring5.api.v1.model.SprintDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.SprintListDTO;
import renza.springframework.kanbanrestspring5.services.SprintService;

@RestController
@RequestMapping(SprintController.BASE_URL)
@CrossOrigin(origins = "${originUrl}", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class SprintController {

    public static final String BASE_URL = "api/v1/sprints";

    private SprintService sprintService;


    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SprintListDTO getListOfSprintDTO(){
        return new SprintListDTO(sprintService.getAllSprints());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SprintDTO getSprintById(@PathVariable Long id){
        return sprintService.getSprintById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SprintDTO createNewSprint(@RequestBody SprintDTO sprintDTO){
        return sprintService.createNewSprint(sprintDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SprintDTO updateSprint(@PathVariable Long id, @RequestBody SprintDTO sprintDTO){

        return sprintService.saveProjectByDto(id, sprintDTO);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSprint(@PathVariable Long id){
        sprintService.deleteProjectById(id);
    }




}
