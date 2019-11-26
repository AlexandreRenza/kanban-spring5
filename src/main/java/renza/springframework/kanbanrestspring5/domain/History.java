package renza.springframework.kanbanrestspring5.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String status;
    private String description;
    private String criteria;

    @ManyToOne
    private Project project;


}
