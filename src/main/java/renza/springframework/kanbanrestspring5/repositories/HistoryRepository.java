package renza.springframework.kanbanrestspring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import renza.springframework.kanbanrestspring5.domain.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    History findByName(String name);

    @Query(value = "SELECT * FROM HISTORY WHERE SPRINT_ID = :SprintId OR SPRINT_ID IS NULL", nativeQuery = true)
    List<History> findHistoriesToSelect(@Param("SprintId")Long SprintId);

    @Query(value = "SELECT * FROM HISTORY WHERE SPRINT_ID = :SprintId", nativeQuery = true)
    List<History> findHistoriesOnBoard(@Param("SprintId")Long SprintId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE HISTORY h SET h.SPRINT_ID = null WHERE h.SPRINT_ID = ?", nativeQuery = true)
    int setNullSprintToHistories(Long SprintId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE HISTORY h SET h.SPRINT_ID = ? WHERE h.ID = ?", nativeQuery = true)
    int setSprintOfHistories(Long SprintId, Long listHistories);

    @Transactional
    @Modifying
    @Query(value = "UPDATE HISTORY h SET h.IMPL_STATUS = ? WHERE h.ID = ?", nativeQuery = true)
    int setImplStatusHistory( String ImplStatus, Long Id);

}
