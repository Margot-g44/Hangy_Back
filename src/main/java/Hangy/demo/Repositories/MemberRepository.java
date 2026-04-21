package Hangy.demo.Repositories;

import Hangy.demo.Entities.Group;
import Hangy.demo.Entities.Member;
import Hangy.demo.Entities.MemberId;
import Hangy.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, MemberId> {

    List<Member> findByUserId(Long userId);
    List<Member> findByGroupId(Long groupId);

    boolean existsByUserAndGroup(User user, Group group);
}
