package com.timesheet.repository;

import com.timesheet.entity.MaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaUserRepository extends JpaRepository<MaUser , Long> {

    Optional<MaUser> findByEmail(String username);
}
