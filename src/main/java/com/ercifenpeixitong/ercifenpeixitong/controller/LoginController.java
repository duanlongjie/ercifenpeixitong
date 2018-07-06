package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Role;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import com.ercifenpeixitong.ercifenpeixitong.service.PermissionService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {
    @Resource
    private UserServiece userServiece;
    @Resource
    private PermissionService permissionService;
    //后台用户登陆
    @RequestMapping("login")
    public String login(Model model){
        model.addAttribute("error","1");
        return "admin/login";
    }
    @RequestMapping("loginOut")
    public String loginOut(){
        return "admin/login";
    }

    @RequestMapping("loginHandler")
    public String loginHandler(String username, String password,
                               HttpSession session,Model model){
        List<String> permissionHrefs =new ArrayList<>();
        ResultInfo<User> resultInfo = userServiece.findByUserNameAndPassword(username, password);
        User user = resultInfo.getResultObj();
        //用户不存在 重新登陆
        if(user==null){
            model.addAttribute("error","错误的用户名或者密码!");
            return "admin/login";
        }
        //用户身份合法  可以登陆
        session.setAttribute("token",user.getId());
        List<Role> roles = user.getRoles();
        for(Role r:roles){
            List<Permission> permissions = r.getPermissions();
            for(Permission p:permissions){
                String href = p.getHref();
                permissionHrefs.add(href);
            }
        }
        session.setAttribute("permissionHrefs",permissionHrefs);
        return "redirect:home";
    }
    @RequestMapping("home")
    public String home(HttpSession session,Model model){

        User user=null;
        List<Permission> permissionList  =new ArrayList<>();
        List<Permission> permissionList2  =new ArrayList<>();
        List<String> hrefs =new ArrayList<>();
        String roleNames ="";
        Object token = session.getAttribute("token");
        if(token instanceof Integer){
            ResultInfo<User> resultInfo = userServiece.findById((Integer) token);
             User user1 = resultInfo.getResultObj();
            ResultInfo<User> resultInfo1 = userServiece.findByUserNameAndPassword(user1.getUsername(), user1.getPassword());
            user = resultInfo1.getResultObj();
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
        List<Role> roles = user.getRoles();
        for(Role r:roles){
            if(!roleNames.contains(r.getRoleName())){
                String roleName = r.getRoleName();
                roleNames=roleNames+"-"+roleName;
            }
        }
        System.out.println("---------------"+roleNames);
        model.addAttribute("user",user);
        model.addAttribute("roleNames",roleNames);
        model.addAttribute("permissionList",permissionList2);
        return "home";
    }


}
