package renza.springframework.kanbanrestspring5.api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SprintListDTO {

    List<SprintDTO> sprints;

}
