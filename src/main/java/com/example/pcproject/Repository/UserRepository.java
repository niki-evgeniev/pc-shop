package com.example.pcproject.Repository;


import com.example.pcproject.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

//    @Query("SELECT u, ip FROM User u JOIN u.ipUser ip WHERE u.id = :userId")
//    List<User> findUserAndIpById(Long userId);

    @Query("SELECT u, ip FROM User u JOIN u.ipUser ip WHERE u.id = :userId")
    Optional<User> findUserAndIpById(Long userId);

}
