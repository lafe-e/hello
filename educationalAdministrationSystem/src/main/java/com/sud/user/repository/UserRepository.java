package com.sud.user.repository;

import com.sud.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    User findByLoginNameAndPassword(String loginName,String password);
    User findByLoginName(String loginName);
    List<User> findAllByLoginNameIsNot(String loginName);
    List<User> findByRole(Integer role);
}
