package com.ercifenpeixitong.ercifenpeixitong.service.impl;

import com.ercifenpeixitong.ercifenpeixitong.constant.ResultCode;
import com.ercifenpeixitong.ercifenpeixitong.dao.PermissionDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;
    @Override
    public ResultInfo<Permission> findByHref(String href) {
        ResultInfo<Permission> resultInfo =new ResultInfo<>();
        if(""!=href){
            Permission permission= permissionDao.findByHref(href);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            resultInfo.setResultObj(permission);
        }
        resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);

        return resultInfo;
    }
}
