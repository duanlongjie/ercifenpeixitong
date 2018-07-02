package com.ercifenpeixitong.ercifenpeixitong.dao;

import com.ercifenpeixitong.ercifenpeixitong.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department,Integer> {
}
