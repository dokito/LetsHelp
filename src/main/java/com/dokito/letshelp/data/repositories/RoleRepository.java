package com.dokito.letshelp.data.repositories;

import com.dokito.letshelp.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByAndAuthority(String authority);
}
