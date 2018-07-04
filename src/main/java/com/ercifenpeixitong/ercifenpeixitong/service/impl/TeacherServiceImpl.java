package com.ercifenpeixitong.ercifenpeixitong.service.impl;

import com.ercifenpeixitong.ercifenpeixitong.constant.ResultCode;
import com.ercifenpeixitong.ercifenpeixitong.constant.TeacherStatus;
import com.ercifenpeixitong.ercifenpeixitong.dao.TeacherDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Resource private TeacherDao teacherDao;
    @Override
    public ResultInfo<Teacher> save(Teacher teacher) {
        ResultInfo<Teacher> resultInfo =new ResultInfo<>();
        resultInfo.setResultObj(teacher);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        Teacher teacher1 = teacherDao.save(teacher);
        return resultInfo;
    }

    @Override
    public ResultInfo<Teacher> findById(String gongHao) {
        ResultInfo<Teacher> resultInfo =new ResultInfo<>();
        if(""!=gongHao){
            Optional<Teacher> optional = teacherDao.findById(gongHao);
            Teacher teacher = optional.get();
            resultInfo.setResultObj(teacher);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        }else {
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<List<Teacher>> getAllTeachers() {
        ResultInfo<List<Teacher>> resultInfo=new ResultInfo<>();
        List<Teacher> teachers = teacherDao.findAllByIsDelete(TeacherStatus.TEACHERUSERFUL);
        resultInfo.setResultObj(teachers);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Teacher> deleteTeacherByGongHao(String gongHao) {
        ResultInfo<Teacher> resultInfo=new ResultInfo<>();
        if(""!=gongHao){
            Optional<Teacher> optional = teacherDao.findById(gongHao);
            Teacher teacher = optional.get();
            teacher.setIsDelete(TeacherStatus.TEACHERDELETE);
            teacherDao.save(teacher);
            resultInfo.setResultObj(teacher);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        }else resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        return resultInfo;
    }
}
