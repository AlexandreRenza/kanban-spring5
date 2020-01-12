package renza.springframework.kanbanrestspring5.services;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import renza.springframework.kanbanrestspring5.api.v1.mapper.HistoryMapper;
import renza.springframework.kanbanrestspring5.api.v1.mapper.ProjectMapper;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryListDTO;
import renza.springframework.kanbanrestspring5.domain.History;
import renza.springframework.kanbanrestspring5.domain.Project;
import renza.springframework.kanbanrestspring5.repositories.HistoryRepository;
import renza.springframework.kanbanrestspring5.repositories.ProjectRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;
    private final HistoryRepository historyRepository;
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    public HistoryServiceImpl(HistoryMapper historyMapper, HistoryRepository historyRepository, ProjectMapper projectMapper, ProjectRepository projectRepository) {
        this.historyMapper = historyMapper;
        this.historyRepository = historyRepository;
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<HistoryDTO> getAllHistories() {
        return historyRepository
                .findAll()
                .stream()
                .map(history -> {
                    HistoryDTO historyDTO = historyMapper.historyToHistoryDTO(history);
                    return historyDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDTO> getAllHistoriesToSelect(Long SprintId) {
        return historyRepository
                .findHistoriesToSelect(SprintId)
                .stream()
                .map(history -> {
                    HistoryDTO historyDTO = historyMapper.historyToHistoryDTO(history);
                    return historyDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDTO> getAllHistoriesOnBoard(Long SprintId) {
        return historyRepository
                .findHistoriesOnBoard(SprintId)
                .stream()
                .map(history -> {
                    HistoryDTO historyDTO = historyMapper.historyToHistoryDTO(history);
                    return historyDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public HistoryDTO getHistoryByID(Long Id) {
        return historyRepository
                .findById(Id)
                .map(historyMapper :: historyToHistoryDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public HistoryDTO createNewHistory(HistoryDTO historyDTO) {

        return saveAndReturnDTO(historyMapper.hitoryDTOToHistory(historyDTO), historyDTO.getProject_id());
    }

    private HistoryDTO saveAndReturnDTO(History history, Long project_id){

       // History savedHistory = historyRepository.save(history);
       // HistoryDTO returnDTO = historyMapper.historyToHistoryDTO(savedHistory);

        HistoryDTO returnDTO;
        Boolean idIsNull = false;

        Optional<Project> projectOptional = projectRepository.findById(project_id);

        Project project = projectOptional.get();
        if(history.getId() == null) {
            project.addHistory(history);
            idIsNull = true;
        }else{

            Optional<History> updateHistory = project.getHistories().stream()
                    .filter(projectHistories -> projectHistories.getId().equals(history.getId()))
                    .findFirst();

            History historyFound = updateHistory.get();
            historyFound.setName(history.getName());
            historyFound.setStatus(history.getStatus());
            historyFound.setImplStatus(history.getImplStatus());
            historyFound.setCriteria(history.getCriteria());
            historyFound.setDescription(history.getDescription());
        }

        Project savedProject = projectRepository.save(project);

        Optional<History> savedHistory = savedProject.getHistories().stream()
                .filter( idIsNull ?  projectHistories -> (projectHistories.getProject().getId().equals(history.getProject().getId())
                                            && projectHistories.getName().equals(history.getName()))
                                          : projectHistories -> projectHistories.getId().equals(history.getId()))
                .findFirst();

        returnDTO = historyMapper.historyToHistoryDTO(savedHistory.get());

        return returnDTO;

    }

    @Override
    public HistoryDTO saveHistoryByDto(Long id, HistoryDTO historyDTO) {

        History history = historyMapper.hitoryDTOToHistory(historyDTO);
        history.setId(id);

        HistoryDTO savedHistoryDTO = saveAndReturnDTO(history, historyDTO.getProject_id());

        return savedHistoryDTO;
    }

    @Override
    public void updateSprintOfHistory(Long sprint_id, List<HistoryDTO> histories )  {

        historyRepository.setNullSprintToHistories(sprint_id);
        for (HistoryDTO history : histories) {
            historyRepository.setSprintOfHistories(sprint_id, history.getId());
        }
    }

    @Override
    public void updateSprintOfHistory(String implStatus, Long Id) {

        historyRepository.setImplStatusHistory(implStatus, Id);

    }

    @Override
    public void deleteHistory(Long id) {

        historyRepository.deleteById(id);

    }
}
