package Hangy.demo.Entities;

import jakarta.persistence.*;
import jdk.jfr.Event;
import org.apache.catalina.Group;
import org.apache.logging.log4j.message.Message;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_users_username", columnNames = "username")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(length = 255)
    private String avatar;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "owner")
    private List<Group> ownedGroups;

    @OneToMany(mappedBy = "user")
    private List<Member> memberships;

    @OneToMany(mappedBy = "createdBy")
    private List<Event> createdEvents;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}