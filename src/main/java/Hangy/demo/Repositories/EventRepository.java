package Hangy.demo.Repositories;

import Hangy.demo.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByGroupId(Long groupId);
    List<Event> findByCreatedById(Long userId);
}
