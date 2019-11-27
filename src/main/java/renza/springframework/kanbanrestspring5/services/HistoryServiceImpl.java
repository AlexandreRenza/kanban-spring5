package renza.springframework.kanbanrestspring5.services;

import org.springframework.stereotype.Service;
import renza.springframework.kanbanrestspring5.api.v1.mapper.HistoryMapper;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.domain.History;
import renza.springframework.kanbanrestspring5.repositories.HistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryMapper historyMapper, HistoryRepository historyRepository) {
        this.historyMapper = historyMapper;
        this.historyRepository = historyRepository;
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
    public HistoryDTO getHistoryByID(Long Id) {
        return historyRepository
                .findById(Id)
                .map(historyMapper :: historyToHistoryDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public HistoryDTO createNewHistory(HistoryDTO historyDTO) {

        return saveAndReturnDTO(historyMapper.hitoryDTOToHistory(historyDTO));
    }

    private HistoryDTO saveAndReturnDTO(History history){

        History savedHistory = historyRepository.save(history);

        HistoryDTO returnDTO = historyMapper.historyToHistoryDTO(savedHistory);

        return returnDTO;

    }

    @Override
    public HistoryDTO saveHistoryByDto(Long id, HistoryDTO historyDTO) {

        History history = historyMapper.hitoryDTOToHistory(historyDTO);
        history.setId(id);

        HistoryDTO savedHistoryDTO = saveAndReturnDTO(history);

        return savedHistoryDTO;
    }

    @Override
    public void deleteHistory(Long id) {

        historyRepository.deleteById(id);

    }
}
