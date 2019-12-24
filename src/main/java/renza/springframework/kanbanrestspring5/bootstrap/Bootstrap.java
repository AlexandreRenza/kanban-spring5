package renza.springframework.kanbanrestspring5.bootstrap;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import renza.springframework.kanbanrestspring5.domain.History;
import renza.springframework.kanbanrestspring5.domain.Project;
import renza.springframework.kanbanrestspring5.domain.Sprint;
import renza.springframework.kanbanrestspring5.repositories.HistoryRepository;
import renza.springframework.kanbanrestspring5.repositories.ProjectRepository;
import renza.springframework.kanbanrestspring5.repositories.SprintRepository;

import java.time.LocalDate;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final HistoryRepository historyRepository;
    private final SprintRepository sprintRepository;


    public Bootstrap(ProjectRepository projectRepository, HistoryRepository historyRepository, SprintRepository sprintRepository) {
        this.projectRepository = projectRepository;
        this.historyRepository = historyRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadProjects();

    }

    private void loadProjects() {

        Project project1 = new Project();
        project1.setName("Projeto Kanban");
        project1.setStatus("In Progress");
        projectRepository.save(project1);

        LocalDate localDate = LocalDate.now();

        Sprint sprint1 = new Sprint();
        sprint1.setName("Sprint 1 - Projeto 1");
        sprint1.setGoal("Finalizar");
        sprint1.setStartDate(localDate);
        sprint1.setEndDate(localDate.plusDays(20));
        sprint1.setProject(project1);
        sprintRepository.save(sprint1);


        Sprint sprint2 = new Sprint();
        sprint2.setName("Sprint 2 - Projeto 1");
        sprint2.setGoal("Testar Tudo");
        sprint2.setStartDate(localDate);
        sprint2.setEndDate(localDate.plusDays(20));
        sprint2.setProject(project1);
        sprintRepository.save(sprint2);


        History history1 = new History();
        history1.setName("Historia 1");
        history1.setDescription("Tudo que temos que fazer na história 1");
        history1.setCriteria("criterios de aceitação história 1");
        history1.setStatus("Acepted");
        history1.setProject(project1);
        history1.setSprint(sprint1);
        historyRepository.save(history1);

        History history2 = new History();
        history2.setName("Historia 2");
        history2.setDescription("Tudo que temos que fazer na história 2");
        history2.setCriteria("criterios de aceitação história 2");
        history2.setStatus("Acepted");
        history2.setProject(project1);
        history2.setSprint(sprint1);
        historyRepository.save(history2);

        History history3 = new History();
        history3.setName("Historia 3");
        history3.setDescription("Tudo que temos que fazer na história 3");
        history3.setCriteria("criterios de aceitação história 3");
        history3.setStatus("Acepted");
        history3.setProject(project1);
        historyRepository.save(history3);

        History history4 = new History();
        history4.setName("Historia 4");
        history4.setDescription("Tudo que temos que fazer na história 4");
        history4.setCriteria("criterios de aceitação história 4");
        history4.setStatus("Acepted");
        history4.setProject(project1);
        historyRepository.save(history4);

        Project project2 = new Project();
        project2.setName("Projeto GEOMKT");
        project2.setStatus("In Progress");
        projectRepository.save(project2);

        System.out.println("Projects Loaded: " + projectRepository.count());



    }


}
