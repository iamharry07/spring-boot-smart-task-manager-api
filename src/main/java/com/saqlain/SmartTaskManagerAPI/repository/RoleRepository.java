package com.saqlain.SmartTaskManagerAPI.repository;

import com.saqlain.SmartTaskManagerAPI.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {



}
