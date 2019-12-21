package renza.springframework.kanbanrestspring5.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import renza.springframework.kanbanrestspring5.api.v1.mapper.SprintMapper;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.SprintDTO;
import renza.springframework.kanbanrestspring5.domain.History;
import renza.springframework.kanbanrestspring5.domain.Project;
import renza.springframework.kanbanrestspring5.domain.Sprint;
import renza.springframework.kanbanrestspring5.repositories.HistoryRepository;
import renza.springframework.kanbanrestspring5.repositories.ProjectRepository;
import renza.springframework.kanbanrestspring5.repositories.SprintRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {

    private final SprintMapper sprintMapper;
    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;
    private final HistoryRepository historyRepository;

    public SprintServiceImpl(SprintMapper sprintMapper, SprintRepository sprintRepository, ProjectRepository projectRepository, HistoryRepository historyRepository) {
        this.sprintMapper = sprintMapper;
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
        this.historyRepository = historyRepository;
    }


    @Override
    public List<SprintDTO> getAllSprints() {
        return sprintRepository
                .findAll()
                .stream()
                .map(sprint -> {
                     SprintDTO sprintDTO = sprintMapper.sprintToSprintDTO(sprint);
                     return sprintDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public SprintDTO getSprintById(Long id) {
        return sprintRepository
                .findById(id)
                .map(sprintMapper :: sprintToSprintDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public SprintDTO createNewSprint(SprintDTO sprintDTO) {
        return saveAndReturnDTO(sprintMapper.sprintDTOToSprint(sprintDTO),sprintDTO.getProject_id()) ;
    }


    private SprintDTO saveAndReturnDTO(Sprint sprint, Long project_id){

        SprintDTO returnDTO;
        Boolean idIsNull = false;

        Optional<Project> projectOptional = projectRepository.findById(project_id);

        Project project = projectOptional.get();

        if(sprint.getId()==null){
            project.addSprint(sprint);
            idIsNull = true;

        }else {
            Optional<Sprint> updateSprint = project.getSprints().stream()
                    .filter(projectSprints -> projectSprints.getId().equals(sprint.getId()))
                    .findFirst();
            Sprint sprintFound = updateSprint.get();
            sprintFound.setName(sprint.getName());
            sprintFound.setGoal(sprint.getGoal());
            sprintFound.setStartDate(sprint.getStartDate());
            sprintFound.setEndDate(sprint.getEndDate());
        }


        Project savedProject = projectRepository.saveAndFlush(project);

        Optional<Sprint> savedSprint = savedProject.getSprints().stream()
                .filter(idIsNull ? projectSprints -> (projectSprints.getProject().getId().equals(sprint.getProject().getId())
                                                      && projectSprints.getName().equals(sprint.getName()))
                                 : projectSprints -> projectSprints.getId().equals(sprint.getId()))
                .findFirst();


        Sprint sprintsaved = savedSprint.get();

        if(sprint.getHistories() != null){

            for( History valor : sprint.getHistories()){

                Optional<History> updateHistory = project.getHistories().stream()
                        .filter(history -> history.getId().equals(valor.getId()))
                        .findFirst();
                History historyFound = updateHistory.get();

                String option = "i";

                if( (valor.getOp()).equals(option)){
                    sprintsaved.addHistory(historyFound);
                }else{
                    historyFound.setSprint(null);
                    sprintsaved.remHistory(historyFound);
                }
            }
        }

        Sprint reloadSprint = sprintRepository.save(sprintsaved);

        returnDTO = sprintMapper.sprintToSprintDTO(reloadSprint);

        return returnDTO;
    }

    @Override
    public SprintDTO saveProjectByDto(Long id, SprintDTO sprintDTO) {

        Sprint sprint = sprintMapper.sprintDTOToSprint(sprintDTO);
        sprint.setId(id);

        SprintDTO savedSprintDTO = saveAndReturnDTO(sprint, sprintDTO.getProject_id());

        return savedSprintDTO;
    }

    @Override
    public void deleteProjectById(Long id) {

        sprintRepository.deleteById(id);

    }
}
