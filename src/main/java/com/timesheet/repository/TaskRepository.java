package com.timesheet.repository;

import com.timesheet.entity.Project;
import com.timesheet.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Modifying
    @Query(value = "delete from task_user tu where tu.task_id=:taskId",nativeQuery = true)
    void deleteProjectUsersByProjectId(@Param("taskId") Long taskId);
}
