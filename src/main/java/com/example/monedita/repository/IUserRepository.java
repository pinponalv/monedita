package com.example.monedita.repository;

import com.example.monedita.entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec,Long> {
    Optional<UserSec> findByUsername(String username);
}
