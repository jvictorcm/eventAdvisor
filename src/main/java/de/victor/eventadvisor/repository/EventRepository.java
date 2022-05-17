package de.victor.eventadvisor.repository;

import de.victor.eventadvisor.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE LOWER(e.bezeichnung) LIKE lower(concat('%', :input,'%')) ORDER BY e.eventScore DESC")
    List<Event> findBierEvents(@Param("input") String input);

    List<Event> findAllByBezirkOrderByEventScoreDesc(String district);

    @Transactional
    @Modifying
    @Query(value = "update event e set e.event_score = (e.event_score + :vote) where e.id = :id", nativeQuery = true)
    void voteEvent(@Param("vote") Integer vote, @Param("id") Long id);

    List<Event> findTop10ByOrderByEventScoreDesc();
}
