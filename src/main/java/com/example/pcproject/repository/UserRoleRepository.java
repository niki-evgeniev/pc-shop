package com.example.pcproject.repository;

import com.example.pcproject.models.entity.UserRole;
import com.example.pcproject.models.eunums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByRolesIn(List<RoleType> types);
}
