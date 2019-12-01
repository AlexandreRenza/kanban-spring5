package renza.springframework.kanbanrestspring5.domain;


import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
    private Set<History> histories  = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Sprint> sprints  = new HashSet<>();


    public Project addHistory(History history){
        history.setProject(this);
        this.histories.add(history);
        return this;
    }

    public Project addSprint(Sprint sprint){
        sprint.setProject(this);
        this.sprints.add(sprint);
        return this;
    }

}
