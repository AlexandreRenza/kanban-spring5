package renza.springframework.kanbanrestspring5.api.v1.model;

import lombok.Data;

@Data
public class ProjectDTO {

    private Long id;
    private String name;
    private String status;

}
