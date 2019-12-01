package renza.springframework.kanbanrestspring5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryDTO;
import renza.springframework.kanbanrestspring5.api.v1.model.HistoryListDTO;
import renza.springframework.kanbanrestspring5.services.HistoryService;

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHistory(@PathVariable Long id){
        historyService.deleteHistory(id);
    }



}
