package com.ercifenpeixitong.ercifenpeixitong.dao;

import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherDao extends JpaRepository<Teacher,String> {
    List<Teacher> findAllByIsDelete(Integer isDelete);
    Teacher findByGongHaoAndPassword(String gongHao,String password);
}
