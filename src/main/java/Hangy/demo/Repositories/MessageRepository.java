package Hangy.demo.Repositories;

import Hangy.demo.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByGroupId(Long groupId);
    List<Message> findByUserId(Long userId);
}
