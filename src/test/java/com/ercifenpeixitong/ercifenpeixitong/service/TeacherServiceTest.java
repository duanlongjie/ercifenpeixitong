package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.domain.Declaration;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {
    @Resource
    private TeacherService teacherService;
    @Test
    public void test(){
        ResultInfo<Teacher> resultInfo = teacherService.findById("1");
        Teacher teacher = resultInfo.getResultObj();
         teacher.getDeclarations().clear();
        teacherService.save(teacher);
    }
}
