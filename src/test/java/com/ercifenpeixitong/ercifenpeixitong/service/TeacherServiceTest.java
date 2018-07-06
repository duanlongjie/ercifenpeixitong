package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.dao.StandardDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.Declaration;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Standard;
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
    @Resource
    private DeclarationService declarationService;
    @Resource
    private StandardDao standardDao;
    @Test
    public void test(){
        ResultInfo<Teacher> resultInfo = teacherService.findById("1");
        Teacher teacher = resultInfo.getResultObj();
         teacher.getDeclarations().clear();
        teacherService.save(teacher);
    }
    @Test
    public void t(){
        ResultInfo<Teacher> resultInfo = teacherService.findById("10086");
        Teacher teacher = resultInfo.getResultObj();
        ResultInfo<Declaration> resultInfo1 = declarationService.findDecById("1");
        Declaration declaration = resultInfo1.getResultObj();
        Standard s=new Standard();
        s.setStandard("1p");
        declaration.getStandards().add(s);
        teacher.getDeclarations().add(declaration);
        teacherService.save(teacher);


    }
    @Test
    public void t2(){
        ResultInfo<Teacher> resultInfo = teacherService.findById("10086");
        Teacher teacher = resultInfo.getResultObj();
        List<Declaration> declarations = teacher.getDeclarations();
        for(Declaration d:declarations){
            List<Standard> standards = d.getStandards();
            System.out.println(standards.size());
        }
    }

}
