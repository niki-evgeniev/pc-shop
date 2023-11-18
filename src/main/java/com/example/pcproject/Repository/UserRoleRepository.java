package com.example.pcproject.Repository;

import com.example.pcproject.models.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole ,Long> {
}
