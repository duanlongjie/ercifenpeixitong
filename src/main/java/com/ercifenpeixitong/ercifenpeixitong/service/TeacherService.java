package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * 保存用户信息
     * @param teacher 教师信息
     * @return  返回 保存教师信息
     */
    ResultInfo<Teacher> save(Teacher teacher);

    /**
     * 根据老师的工号查找教师信息
     * @param gongHao  教师工号
     * @return 返回教师信息
     */
    ResultInfo<Teacher> findById(String gongHao);

    /**
     * 获取所有教师信息(未删除的)
     * @return 返回删除教师信息
     */
    ResultInfo<List<Teacher>> getAllTeachers();

    /**
     * 根据教师工号 删除教师
     */
    ResultInfo<Teacher> deleteTeacherByGongHao(String gongHao);

}
