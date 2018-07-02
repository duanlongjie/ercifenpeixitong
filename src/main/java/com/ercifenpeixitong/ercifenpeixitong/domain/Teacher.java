package com.ercifenpeixitong.ercifenpeixitong.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师和 申报名称是多对多关系
 */
@Entity
@Table(name = "ec_teacher")
public class Teacher {
    @Id
    private String gongHao;
    private String password;
    private String name;
    private Integer isDelete;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Declaration> declarations =new ArrayList<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "gongHao='" + gongHao + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isDelete=" + isDelete +
                ", declarations=" + declarations +
                '}';
    }

    public String getGongHao() {
        return gongHao;
    }

    public void setGongHao(String gongHao) {
        this.gongHao = gongHao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }
}
