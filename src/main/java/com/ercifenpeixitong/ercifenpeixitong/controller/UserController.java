package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserServiece userServiece;



}
