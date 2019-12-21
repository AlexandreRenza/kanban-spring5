package renza.springframework.kanbanrestspring5.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import renza.springframework.kanbanrestspring5.domain.History;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class SprintDTO {

    private Long Id;
    private Long project_id;
    private String name;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonIgnoreProperties({"sprint","project"})
    private Set<History> histories;





}
