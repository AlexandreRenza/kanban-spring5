package renza.springframework.kanbanrestspring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renza.springframework.kanbanrestspring5.domain.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Sprint findByName(String name);

}
