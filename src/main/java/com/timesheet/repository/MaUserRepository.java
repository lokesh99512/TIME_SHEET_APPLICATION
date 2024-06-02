package com.timesheet.repository;

import com.timesheet.entity.MaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaUserRepository extends JpaRepository<MaUser , Long> {

    Optional<MaUser> findByEmail(String username);

    @Query("select mu from MaUser mu where mu.maCompany.id= :companyId")
    Page<MaUser> findAllUserByCompanyId(@Param("companyId") Long companyId, Pageable pageable);

    @Query("SELECT mu FROM MaUser mu WHERE mu.maCompany.id = :companyId AND mu.roles LIKE '%ADMIN%' AND mu.status = 'ACTIVE'")
    List<MaUser> findAllUserByRoleAdmin(@Param("companyId") Long companyId);
}
