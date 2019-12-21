package renza.springframework.kanbanrestspring5.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import renza.springframework.kanbanrestspring5.domain.History;
import renza.springframework.kanbanrestspring5.domain.Sprint;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private String status;
    @JsonIgnoreProperties({"project","sprint"})
    private Set<History> histories;
    @JsonIgnoreProperties({"project","histories"})
    private Set<Sprint> sprints;


}
