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
    /**申报状态 0:申报成功 1:申报失败  2:申报中 3:尚无申报*/
    private Integer declareStatus;
    private String qq;
    private String gangWei;
    private String xingZhen;
    private String zhiCheng;
    /**额定完成教学*/
    private float edJiao=140;
    /**教学实际完成  1分 5元*/
    private float shijiJiao;
    /**额定完成科研*/
    private float edKe=40;
    private float shijiKe;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Declaration> declarations =new ArrayList<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "gongHao='" + gongHao + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isDelete=" + isDelete +
                ", declareStatus=" + declareStatus +
                ", declarations=" + declarations +
                '}';
    }

    public String getGangWei() {
        return gangWei;
    }

    public void setGangWei(String gangWei) {
        this.gangWei = gangWei;
    }

    public String getXingZhen() {
        return xingZhen;
    }

    public void setXingZhen(String xingZhen) {
        this.xingZhen = xingZhen;
    }

    public String getZhiCheng() {
        return zhiCheng;
    }

    public void setZhiCheng(String zhiCheng) {
        this.zhiCheng = zhiCheng;
    }

    public float getEdJiao() {
        return edJiao;
    }

    public void setEdJiao(float edJiao) {
        this.edJiao = edJiao;
    }

    public float getShijiJiao() {
        return shijiJiao;
    }

    public void setShijiJiao(float shijiJiao) {
        this.shijiJiao = shijiJiao;
    }

    public float getEdKe() {
        return edKe;
    }

    public void setEdKe(float edKe) {
        this.edKe = edKe;
    }

    public float getShijiKe() {
        return shijiKe;
    }

    public void setShijiKe(float shijiKe) {
        this.shijiKe = shijiKe;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getDeclareStatus() {
        return declareStatus;
    }

    public void setDeclareStatus(Integer declareStatus) {
        this.declareStatus = declareStatus;
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
