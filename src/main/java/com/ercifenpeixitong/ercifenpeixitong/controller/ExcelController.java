package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.dao.DepartmentDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.*;
import com.ercifenpeixitong.ercifenpeixitong.service.PermissionService;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import com.ercifenpeixitong.ercifenpeixitong.utils.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class ExcelController {
    @Resource
    private UserServiece userServiece;
    @Resource
    private TeacherService teacherService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private DepartmentDao departmentDao;
    @RequestMapping("import")
    public String importExcel(HttpSession session, Model model) throws Exception{
        ResultInfo<List<Teacher>> resultInfo1 =
                teacherService.getAllTeachers();
        List<Teacher> teachers1 = resultInfo1.getResultObj();
        Map<String,String> map=new HashMap<>();
        map.put("name","姓名"); map.put("gongHao","工号");
        map.put("qq","QQ");map.put("gangWei","岗位");
        map.put("xingZhen","行政部门");
        map.put("edJiao","额定完成教学");map.put("zhiCheng","职称");
        map.put("shijiJiao","实际完成教学");map.put("edKe","额定完成科研");
        map.put("shijiKe","实际完成科研");
        OutputStream o=new FileOutputStream("C:/Users/刘腾飞/Desktop/teachers.xls");
        ExcelUtil.<Teacher>listToExcel(teachers1,map,"teachers",123,o);



        User user=null;
        String roleNames ="";
        List<Permission> permissionList  =new ArrayList<>();
        List<Permission> permissionList2  =new ArrayList<>();
        List<String> hrefs =new ArrayList<>();
        Object token = session.getAttribute("token");
        if(token instanceof Integer){
            ResultInfo<User> resultInfo = userServiece.findById((Integer) token);
            user = resultInfo.getResultObj();
            if(user!=null){
                List<Role> roles = user.getRoles();
                for(Role role:roles){
                    List<Permission> permissions = role.getPermissions();
                    for(Permission permission:permissions){
                        permissionList.add(permission);
                    }
                }
            }
        }
        for(Permission p:permissionList){
            if(!hrefs.contains(p.getHref())){
                hrefs.add(p.getHref());
            }
        }
        for(String s:hrefs){
            ResultInfo<Permission> resultInfo2 = permissionService.findByHref(s);
            Permission pe = resultInfo2.getResultObj();
            permissionList2.add(pe);
        }

        model.addAttribute("permissionList",permissionList2);
        ResultInfo<List<Teacher>> resultInfo = teacherService.getAllTeachers();
        List<Teacher> teachers = resultInfo.getResultObj();
        model.addAttribute("teachers",teachers);
        List<Role> roles = user.getRoles();
        for(Role r:roles){
            String roleName = r.getRoleName();
            roleNames=roleNames+"-"+roleName;
        }
        System.out.println("----------------------roleNames:"+roleNames);
        model.addAttribute("user",user);
        model.addAttribute("roleNames",roleNames);
        model.addAttribute("permissionList",permissionList2);
        model.addAttribute("info","导出成功!");
        return "admin/teacherList";
    }



    @RequestMapping("gongshi")
    public String gongshi(HttpSession session, Model model){
        Optional<Department> optional = departmentDao.findById(1);
        Department department = optional.get();
        department.setIsDelete(1);
        departmentDao.save(department);
        User user=null;
        String roleNames ="";
        List<Permission> permissionList  =new ArrayList<>();
        List<Permission> permissionList2  =new ArrayList<>();
        List<String> hrefs =new ArrayList<>();
        Object token = session.getAttribute("token");
        if(token instanceof Integer){
            ResultInfo<User> resultInfo = userServiece.findById((Integer) token);
            user = resultInfo.getResultObj();
            if(user!=null){
                List<Role> roles = user.getRoles();
                for(Role role:roles){
                    List<Permission> permissions = role.getPermissions();
                    for(Permission permission:permissions){
                        permissionList.add(permission);
                    }
                }
            }
        }
        for(Permission p:permissionList){
            if(!hrefs.contains(p.getHref())){
                hrefs.add(p.getHref());
            }
        }
        for(String s:hrefs){
            ResultInfo<Permission> resultInfo2 = permissionService.findByHref(s);
            Permission pe = resultInfo2.getResultObj();
            permissionList2.add(pe);
        }

        model.addAttribute("permissionList",permissionList2);
        ResultInfo<List<Teacher>> resultInfo = teacherService.getAllTeachers();
        List<Teacher> teachers = resultInfo.getResultObj();
        model.addAttribute("teachers",teachers);
        List<Role> roles = user.getRoles();
        for(Role r:roles){
            String roleName = r.getRoleName();
            roleNames=roleNames+"-"+roleName;
        }
        System.out.println("----------------------roleNames:"+roleNames);
        model.addAttribute("user",user);
        model.addAttribute("roleNames",roleNames);
        model.addAttribute("permissionList",permissionList2);
        model.addAttribute("info","导出成功!");
        return "admin/teacherList";
    }
}
