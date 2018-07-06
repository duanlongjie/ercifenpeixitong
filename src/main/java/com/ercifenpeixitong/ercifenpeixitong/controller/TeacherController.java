package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.domain.*;
import com.ercifenpeixitong.ercifenpeixitong.service.PermissionService;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class TeacherController {
    @Resource
    private UserServiece userServiece;
    @Resource
    private TeacherService teacherService;
    @Resource
    private PermissionService permissionService;
    @RequestMapping("teacherHome")
    public String teacherHome(HttpSession session, Model model){
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
        model.addAttribute("info","");
        model.addAttribute("info1","");
        return "admin/teacherList";
    }

    @RequestMapping("shen")
    public String shen(String gongHao,Model model,HttpSession session){
        session.setAttribute("gongHao",gongHao);
        System.out.println("=================:gongHao"+gongHao);
        ResultInfo<Teacher> resultInfo = teacherService.findById(gongHao);
        Teacher teacher = resultInfo.getResultObj();
        List<Declaration> declarations = teacher.getDeclarations();
        model.addAttribute("teacher",teacher);
            model.addAttribute("declarations",declarations);
        return "admin/shenForm";
    }

    @RequestMapping("personal")
    public String personal(String gongHao,Model model,Integer id){
        ResultInfo<User> resultInfo1 = userServiece.findById(id);
        User user = resultInfo1.getResultObj();
        System.out.println("_________________________________________");
        System.out.println(user);
        System.out.println("_________________________________________");
        ResultInfo<Teacher> resultInfo = teacherService.findById(gongHao);
        Teacher teacher = resultInfo.getResultObj();
        List<Declaration> declarations = teacher.getDeclarations();
        model.addAttribute("declarations",declarations);
        model.addAttribute("teacher",teacher);
        model.addAttribute("user",user);
        return "teacher/overForm";
    }


    @RequestMapping("detil")
    public String detil(String gongHao,Model model){
        ResultInfo<Teacher> resultInfo = teacherService.findById(gongHao);
        Teacher teacher = resultInfo.getResultObj();
        List<Declaration> declarations = teacher.getDeclarations();
        model.addAttribute("declarations",declarations);
        model.addAttribute("teacher",teacher);
        return "teacher/teacherInfo";
    }

}
