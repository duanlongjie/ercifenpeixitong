package com.ercifenpeixitong.ercifenpeixitong.domain;


import javax.persistence.*;

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


    @Override
    public String toString() {
        return "Declaration{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
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

}
