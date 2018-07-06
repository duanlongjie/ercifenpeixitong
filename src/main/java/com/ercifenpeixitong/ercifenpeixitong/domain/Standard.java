package com.ercifenpeixitong.ercifenpeixitong.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 评分标准
 */
@Entity
@Table(name="ec_standard")
public class Standard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**评分标准（参加活动的次数）*/
    private String standard;
    /**和评审人是多对多关系*/
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private List<Department> departments =new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Override
    public String toString() {
        return "Standard{" +
                "id=" + id +
                ", standard='" + standard + '\'' +
                ", user=" + user +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
