package com.cloud9.onboard.repository.data;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud9.onboard.model.db.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}

