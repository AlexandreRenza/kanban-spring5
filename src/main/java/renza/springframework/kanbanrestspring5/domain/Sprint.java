package renza.springframework.kanbanrestspring5.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"project"})
@ToString(exclude = {"project"})
public class Sprint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY)
    private Set<History> histories  = new HashSet<>();


    public Sprint addHistory(History history){
        history.setSprint(this);
        this.histories.add(history);
        return this;
    }


}
