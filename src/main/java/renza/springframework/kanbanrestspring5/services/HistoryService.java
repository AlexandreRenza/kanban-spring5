package renza.springframework.kanbanrestspring5.services;

import org.springframework.boot.configurationprocessor.json.JSONException;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryListDTO;

import java.util.List;

public interface HistoryService {

    List<HistoryDTO> getAllHistories();

    List<HistoryDTO> getAllHistoriesToSelect(Long SprintId);

    List<HistoryDTO> getAllHistoriesOnBoard(Long SprintId);

    HistoryDTO getHistoryByID(Long Id);

    HistoryDTO createNewHistory(HistoryDTO historyDTO);

    HistoryDTO saveHistoryByDto(Long id, HistoryDTO historyDTO);

    void updateSprintOfHistory(Long sprint_id, List<HistoryDTO> histories);

    void updateSprintOfHistory(String implStatus, Long Id);

    void deleteHistory(Long id);

}
