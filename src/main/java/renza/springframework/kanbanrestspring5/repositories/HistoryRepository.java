package renza.springframework.kanbanrestspring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renza.springframework.kanbanrestspring5.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

    History findByName(String name);

}
