package Hangy.demo.Repositories;

import Hangy.demo.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByOwnerId(Long ownerId);
}
