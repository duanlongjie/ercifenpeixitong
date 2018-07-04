package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;

public interface UserServiece {
    ResultInfo<User> findByUserNameAndPassword(String username,String password);
    ResultInfo<User> findById(Integer id);
}
