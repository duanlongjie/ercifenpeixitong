package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.domain.Declaration;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;

import java.util.List;

public interface DeclarationService {
    ResultInfo<Declaration> findDecById(String id);
    ResultInfo<List<Declaration>> findAll();

}
