package renza.springframework.kanbanrestspring5.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.domain.History;

@Mapper
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);


    HistoryDTO historyToHistoryDTO (History history);

    History hitoryDTOToHistory (HistoryDTO historyDTO);


}
