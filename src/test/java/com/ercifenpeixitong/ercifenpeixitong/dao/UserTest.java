package com.ercifenpeixitong.ercifenpeixitong.dao;

import com.ercifenpeixitong.ercifenpeixitong.domain.Department;
import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.Role;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    private UserDao userDao;
    @Test
    public void insertTest(){
        User user =new User();
        user.setIsDelete(0);
        user.setPassword("123"); user.setUsername("段龙杰");
        Department department =new Department();
        department.setIsDelete(0) ; department.setName("学生部");
        Role role =new Role();
        role.setRoleName("超级管理员");
        user.getRoles().add(role);
        Role role1 =new Role();
        role1.setRoleName("普通管理员");
        user.getRoles().add(role1);
        user.setDepartment(department);
        userDao.save(user);
    }

    @Test
    public void insert(){
        User user =new User();
        user.setIsDelete(0);
        user.setPassword("123"); user.setUsername("tom");
        Department department =new Department();
        department.setIsDelete(0) ; department.setName("科研部");
        user.setDepartment(department);
        userDao.save(user);

    }
    @Test
    public void find(){
        Optional<User> byId =
                userDao.findById(1);
        User user = byId.get();
        System.out.println(user.getDepartment());
    }

    @Test
    public void find02(){
        Optional<User> op = userDao.findById(3);
        User user = op.get();
        List<Role> roles =
                user.getRoles();
        System.out.println(roles);
        for (Role role:roles){
            List<Permission> permissions = role.getPermissions();
            for(Permission p:permissions){
                String href = p.getHref();
            }
        }
    }
    @Test
    public void find03(){
        User admin = userDao.findAllByUsernameAndPassword("admin", "123");
        System.out.println(admin);
    }
}
