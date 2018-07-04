package com.ercifenpeixitong.ercifenpeixitong.dao;

import com.ercifenpeixitong.ercifenpeixitong.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
