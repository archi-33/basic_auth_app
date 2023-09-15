package com.practice.basic_auth.repositories;

import com.practice.basic_auth.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
  User findByEmail(String email);
}
