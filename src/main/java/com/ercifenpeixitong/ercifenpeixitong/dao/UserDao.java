package com.ercifenpeixitong.ercifenpeixitong.dao;

import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findAllByUsernameAndPassword(String username,String password);
}
