package renza.springframework.kanbanrestspring5.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "history")
    private Set<History> histories;


}
