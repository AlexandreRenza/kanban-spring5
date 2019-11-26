package renza.springframework.kanbanrestspring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renza.springframework.kanbanrestspring5.domain.Project;

public interface ProjectRepository extends JpaRepository <Project, Long> {

    Project findByName(String name);

}
