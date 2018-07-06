package com.ercifenpeixitong.ercifenpeixitong.utils;

import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Teacher;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {
    @Resource
    private TeacherService teacherService;
    @Test
    public void t() throws Exception{
        ResultInfo<List<Teacher>> resultInfo =
                teacherService.getAllTeachers();
        List<Teacher> teachers = resultInfo.getResultObj();
        Map<String,String>  map=new HashMap<>();
        map.put("name","姓名");map.put("gongHao","工号");
        OutputStream o=new FileOutputStream("C:/projects/ecfpsys/h.xls");
        ExcelUtil.<Teacher>listToExcel(teachers,map,"h",123,o);
    }
}
