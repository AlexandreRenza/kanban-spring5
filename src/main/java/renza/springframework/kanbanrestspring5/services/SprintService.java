package renza.springframework.kanbanrestspring5.services;

import renza.springframework.kanbanrestspring5.api.v1.model.SprintDTO;

import java.util.List;

public interface SprintService {

    List<SprintDTO> getAllSprints();

    SprintDTO getSprintById(Long id);

    SprintDTO createNewSprint(SprintDTO sprintDTO);

    SprintDTO saveProjectByDto(Long id, SprintDTO sprintDTO);

    void deleteProjectById(Long id);




}
