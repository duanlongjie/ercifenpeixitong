package com.ercifenpeixitong.ercifenpeixitong.service.impl;

import com.ercifenpeixitong.ercifenpeixitong.constant.ResultCode;
import com.ercifenpeixitong.ercifenpeixitong.dao.UserDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServieceImpl implements UserServiece {
    @Resource
    private UserDao userDao;
    @Override
    public ResultInfo<User> findByUserNameAndPassword(String username, String password) {
        ResultInfo<User> resultInfo= new ResultInfo<>();
        if(""!=null && ""!=password){
            User user = userDao.findAllByUsernameAndPassword(username, password);
            resultInfo.setResultObj(user);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            return resultInfo;
        }
        return resultInfo;
    }


    @Override
    public ResultInfo<User> findById(Integer id) {
        ResultInfo<User> resultInfo =new ResultInfo<>();
        if(id!=null){
            Optional<User> optional = userDao.findById(id);
            User user = optional.get();
            resultInfo.setResultObj(user);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            return resultInfo;
        }
        return resultInfo;
    }
}
