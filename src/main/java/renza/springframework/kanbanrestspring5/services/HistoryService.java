package renza.springframework.kanbanrestspring5.services;

import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;

import java.util.List;

public interface HistoryService {

    List<HistoryDTO> getAllHistories();

    HistoryDTO getHistoryByID(Long Id);

    HistoryDTO createNewHistory(HistoryDTO historyDTO);

    HistoryDTO saveHistoryByDto(Long id, HistoryDTO historyDTO);

    void deleteHistory(Long id);

}
