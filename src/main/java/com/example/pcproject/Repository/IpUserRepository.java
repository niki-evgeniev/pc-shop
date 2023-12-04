package com.example.pcproject.Repository;

import com.example.pcproject.models.entity.IpUser;
import com.example.pcproject.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IpUserRepository extends JpaRepository<IpUser, Long> {

    Optional<IpUser> findByIp(String ip);
}
