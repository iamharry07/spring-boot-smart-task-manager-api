package com.saqlain.SmartTaskManagerAPI.repository;

import com.saqlain.SmartTaskManagerAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
}
