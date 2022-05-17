package de.victor.eventadvisor.repository;

import de.victor.eventadvisor.model.PublicApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PublicApiRepository extends JpaRepository<PublicApi, Long> {

    @Query("select pa.updatedAt from PublicApi pa where pa.id = 1")
    public LocalDateTime getLastUpdate();

}
