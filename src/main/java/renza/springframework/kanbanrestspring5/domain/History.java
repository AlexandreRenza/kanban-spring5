package renza.springframework.kanbanrestspring5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"project", "sprint"})
@ToString(exclude = { "project", "sprint"})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String status;
    private String description;
    private String criteria;

    @Transient
    private String op;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;


}
