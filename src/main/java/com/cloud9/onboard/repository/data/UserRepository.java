package com.cloud9.onboard.repository.data;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud9.onboard.model.db.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}