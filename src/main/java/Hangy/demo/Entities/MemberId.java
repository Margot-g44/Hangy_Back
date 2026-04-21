package Hangy.demo.Entities;

import java.io.Serializable;
import java.util.Objects;

public class MemberId implements Serializable {
    private Long user;
    private Long group;

    public MemberId() {}

    public MemberId(Long user, Long group) {
        this.user = user;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof MemberId)) return false;
        MemberId that = (MemberId) o;
        return Objects.equals(user, that.user) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, group);
    }
}
