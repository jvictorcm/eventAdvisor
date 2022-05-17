package de.victor.eventadvisor.repository;

import de.victor.eventadvisor.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
