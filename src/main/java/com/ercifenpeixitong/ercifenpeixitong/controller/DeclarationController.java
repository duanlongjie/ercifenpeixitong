package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.constant.TeacherStatus;
import com.ercifenpeixitong.ercifenpeixitong.domain.*;
import com.ercifenpeixitong.ercifenpeixitong.service.DeclarationService;
import com.ercifenpeixitong.ercifenpeixitong.service.PermissionService;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class DeclarationController {
    @Resource
    private DeclarationService declarationService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private UserServiece userServiece;
    @Resource
    private PermissionService permissionService;
    @RequestMapping("getDecs")
    public String decs(Model model){
        ResultInfo<List<Declaration>> resultInfo = declarationService.findAll();
        List<Declaration> declarations = resultInfo.getResultObj();
        model.addAttribute("declarations",declarations);
        return "teacher/teacherForm";
    }

    @ResponseBody
    @RequestMapping("formHandler")
    public void test(final String[]ids, HttpSession session){
        System.out.println("+++++++++++++++++++++");
        for(int i=0;i<ids.length;i++){
            System.out.println(ids[i]);
        }
        System.out.println("+++++++++++++++++++++");
        Teacher teacher=null;
        Object tToken = session.getAttribute("tToken");
        if(tToken instanceof String && ""!=tToken){
            System.out.println("===============================tToken:"+(String) tToken);
            ResultInfo<Teacher> re = teacherService.findById((String) tToken);
             teacher = re.getResultObj();
            System.out.println(teacher);
        }
        for(int i=0;i<ids.length;i++){
            ResultInfo<Declaration> resultInfo = declarationService.findDecById(ids[i]);
            Declaration declaration = resultInfo.getResultObj();
            teacher.getDeclarations().add(declaration);
        }
        //审核中。。
        teacher.setDeclareStatus(TeacherStatus.DECLARESTATUSUNKOWM);
        teacherService.save(teacher);
    }

    @ResponseBody
    @RequestMapping("formHandler2")
    public void test2(final String[]ids, HttpSession session){
        System.out.println("+++++++++++++++++++++");
        for(int i=0;i<ids.length;i++){
            System.out.println(ids[i]);
        }
        System.out.println("+++++++++++++++++++++");
        Teacher teacher=null;
        Object tToken = session.getAttribute("gongHao");
        if(tToken instanceof String && ""!=tToken){
            ResultInfo<Teacher> re = teacherService.findById((String) tToken);
            teacher = re.getResultObj();
            //清除先前的 申报
            teacher.getDeclarations().clear();
            //保存
            teacherService.save(teacher);
        }
        for(int i=0;i<ids.length;i++){
            ResultInfo<Declaration> resultInfo = declarationService.findDecById(ids[i]);
            Declaration declaration = resultInfo.getResultObj();
            //添加通过的申报
            teacher.getDeclarations().add(declaration);
        }
        //审核成功
        teacher.setDeclareStatus(TeacherStatus.DECLARESTATUSSUCCESS);
        teacherService.save(teacher);
    }
    @RequestMapping("pass")
    public String Pass(HttpSession session,Model model){
        Teacher teacher=null;
        Object tToken = session.getAttribute("gongHao");
        if(tToken instanceof String && ""!=tToken){
            ResultInfo<Teacher> re = teacherService.findById((String) tToken);
            teacher = re.getResultObj();
            teacher.setDeclareStatus(TeacherStatus.DECLARESTATUSFAIL);
            teacherService.save(teacher);
        }


        List<Permission> permissionList  =new ArrayList<>();
        List<Permission> permissionList2  =new ArrayList<>();
        List<String> hrefs =new ArrayList<>();
        Object token = session.getAttribute("token");
        if(token instanceof Integer){
            ResultInfo<User> resultInfo = userServiece.findById((Integer) token);
            User user = resultInfo.getResultObj();
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
        return "admin/teacherList";

    }
}
