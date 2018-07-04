package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;

public interface PermissionService {
    ResultInfo<Permission> findByHref(String href);
}
