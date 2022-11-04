package com.lm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lm.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);

}
