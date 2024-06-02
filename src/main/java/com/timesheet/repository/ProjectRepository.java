package com.timesheet.repository;

import com.timesheet.entity.MaUser;
import com.timesheet.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p left join ProjectUser pu on pu.project.id = p.id where p.maCompany.id = :companyId and pu.maUser.id = :userId and p.status = 'ACTIVE'")
    Page<Project> findAllProjectsByCompanyIdAndUserId(@Param("companyId") Long companyId, @Param("userId") Long userId, Pageable pageable);

    @Modifying
    @Query(value = "delete from project_user pu where pu.project_id=:projectId",nativeQuery = true)
    void deleteProjectUsersByProjectId(@Param("projectId") Long projectId);
}
