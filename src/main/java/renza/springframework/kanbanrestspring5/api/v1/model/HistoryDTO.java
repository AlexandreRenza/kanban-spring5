package renza.springframework.kanbanrestspring5.api.v1.model;

import lombok.Data;

@Data
public class HistoryDTO {

    private Long Id;
    private Long project_id;
    private Long sprint_id;
    private String name;
    private String status;
    private String implStatus;
    private String description;
    private String criteria;
    private String op;


}
