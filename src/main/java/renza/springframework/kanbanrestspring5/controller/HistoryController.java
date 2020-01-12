package renza.springframework.kanbanrestspring5.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryListDTO;
import renza.springframework.kanbanrestspring5.services.HistoryService;

import java.util.List;

@RestController
@RequestMapping(HistoryController.BASE_URL)
@CrossOrigin(origins = "${originUrl}", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class HistoryController {

    public static final String BASE_URL = "api/v1/histories";

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HistoryListDTO getListOfHistoryDTO(){

        return new HistoryListDTO(historyService.getAllHistories());

    }


    @GetMapping("/sprint/{sprintId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoryListDTO getListOfHistoryToSelectDTO(@PathVariable Long sprintId){
        return new HistoryListDTO(historyService.getAllHistoriesToSelect(sprintId));
    }

    @GetMapping("/board/{sprintId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoryListDTO getListOfHistoryOnBoardDTO(@PathVariable Long sprintId){
        return new HistoryListDTO(historyService.getAllHistoriesOnBoard(sprintId));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HistoryDTO getHistorybyId(@PathVariable Long id){
        return historyService.getHistoryByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoryDTO createNewHistory(@RequestBody HistoryDTO historyDTO){
        return historyService.createNewHistory(historyDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HistoryDTO updateHistory(@PathVariable Long id, @RequestBody HistoryDTO historyDTO){
        return historyService.saveHistoryByDto(id, historyDTO)  ;
    }


    @PutMapping("sprint/{sprintId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSprintOfHistory(@PathVariable Long sprintId, @RequestBody List<HistoryDTO> histories) {
        historyService.updateSprintOfHistory(sprintId, histories);
    }

    @PutMapping("boardchange/{implStatus}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateImplStatusOfHistory(@PathVariable String implStatus, @PathVariable Long id) {
        historyService.updateSprintOfHistory(implStatus, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHistory(@PathVariable Long id){
        historyService.deleteHistory(id);
    }



}
