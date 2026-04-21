package Hangy.demo.Controllers;

import Hangy.demo.Entities.Group;
import Hangy.demo.Entities.Member;
import Hangy.demo.Services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Créer un groupe
    @PostMapping
    public Group createGroup(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long ownerId) {
        return groupService.createGroup(name, description, ownerId);
    }

    // Ajouter un utilisateur à un groupe
    @PostMapping("/{groupId}/members")
    public Member addMember(@PathVariable Long groupId,
                            @RequestParam Long userId,
                            @RequestParam String role) {
        return groupService.addUserToGroup(groupId, userId, role);
    }

    // Récupérer tous les groupes
    @GetMapping
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }
}
