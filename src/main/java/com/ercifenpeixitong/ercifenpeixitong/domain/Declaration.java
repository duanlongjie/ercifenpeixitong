package com.ercifenpeixitong.ercifenpeixitong.domain;





import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 申报表
 */
@Entity
@Table(name = "ec_declaration")
public class Declaration {
    @Id
    private String id;
    private String name;
    private float score;
    /**0:标志教学 1：标志科研*/
    private Integer remark;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Cascade(value= org.hibernate.annotations.CascadeType.ALL)
    /**joinColumns 本表在第三张表的外键
     * inverseJoinColumns 另外一张表在 第三张表的外键
     **/
    @JoinTable(name = "ec_declaration_standards", joinColumns = @JoinColumn(name = "declaration_id"),
            inverseJoinColumns={@JoinColumn(name="standards_id")})
    private List<Standard> standards=new ArrayList<>();

    @Override
    public String toString() {
        return "Declaration{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public List<Standard> getStandards() {
        return standards;
    }

    public void setStandards(List<Standard> standards) {
        this.standards = standards;
    }
}
