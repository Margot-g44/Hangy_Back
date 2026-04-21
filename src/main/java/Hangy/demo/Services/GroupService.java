package Hangy.demo.Services;

import Hangy.demo.Entities.Group;
import Hangy.demo.Entities.Member;
import Hangy.demo.Entities.User;
import Hangy.demo.Repositories.GroupRepository;
import Hangy.demo.Repositories.MemberRepository;
import Hangy.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository, MemberRepository memberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    // Créer un groupe avec un propriétaire (userId)
    public Group createGroup(String name, String description, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        group.setOwner(owner);
        group.setCreatedAt(LocalDateTime.now());

        Group savedGroup = groupRepository.save(group);

        // Ajouter automatiquement le propriétaire comme membre ADMIN
        Member member = new Member();
        member.setGroup(savedGroup);
        member.setUser(owner);
        member.setRole("ADMIN");
        member.setJoinedAt(LocalDateTime.now());

        memberRepository.save(member);

        return savedGroup;
    }

    // Ajouter un utilisateur à un groupe
    public Member addUserToGroup(Long groupId, Long userId, String role) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Vérifie si l'utilisateur est déjà membre
        boolean exists = memberRepository.existsByUserAndGroup(user, group);
        if (exists) {
            throw new RuntimeException("User is already a member of this group");
        }

        Member member = new Member();
        member.setGroup(group);
        member.setUser(user);
        member.setRole(role.toLowerCase());
        member.setJoinedAt(LocalDateTime.now());

        return memberRepository.save(member);
    }

    // Récupérer tous les groupes
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
