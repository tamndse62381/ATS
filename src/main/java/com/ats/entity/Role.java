package com.ats.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    private int id;
    private String roleName;
    private List<Users> usersById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RoleName", nullable = true, length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public List<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(List<Users> usersById) {
        this.usersById = usersById;
    }
}
