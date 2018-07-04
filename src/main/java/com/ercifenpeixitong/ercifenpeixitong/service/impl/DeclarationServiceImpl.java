package com.ercifenpeixitong.ercifenpeixitong.service.impl;

import com.ercifenpeixitong.ercifenpeixitong.constant.ResultCode;
import com.ercifenpeixitong.ercifenpeixitong.dao.DeclarationDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.Declaration;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.service.DeclarationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DeclarationServiceImpl implements DeclarationService {
    @Resource
    private DeclarationDao declarationDao;
    @Override
    public ResultInfo<Declaration> findDecById(String id) {
        ResultInfo<Declaration> resultInfo =new ResultInfo<>();
        if(""!=id){
            Optional<Declaration> optional = declarationDao.findById(id);
            Declaration declaration = optional.get();
            resultInfo.setResultObj(declaration);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        }
        resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<Declaration>> findAll() {
        ResultInfo<List<Declaration>> resultInfo =new ResultInfo<>();
        List<Declaration> declarations = declarationDao.findAll();
        resultInfo.setResultObj(declarations);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }
}
