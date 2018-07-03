package com.ercifenpeixitong.ercifenpeixitong.service.impl;

import com.ercifenpeixitong.ercifenpeixitong.dao.TeacherDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Resource private TeacherDao teacherDao;
    @Override
    public ResultInfo<Teacher> save(Teacher teacher) {

        return null;
    }

    @Override
    public ResultInfo<Teacher> findById(String gongHao) {
        return null;
    }

    @Override
    public ResultInfo<List<Teacher>> getAllTeachers() {
        return null;
    }

    @Override
    public ResultInfo<Teacher> deleteTeacherByGongHao(String gongHao) {
        return null;
    }
}
