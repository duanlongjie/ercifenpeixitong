package com.ercifenpeixitong.ercifenpeixitong.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门和 用户多都一关系
 */
@Table(name = "ec_department")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer isDelete;
    /**双向访问时为什么用 Set 而不能用 List*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users =new HashSet<>();

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDelete=" + isDelete +
                ", users=" + users +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
