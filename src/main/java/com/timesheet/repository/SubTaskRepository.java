package com.timesheet.repository;

import com.timesheet.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask ,Long> {
}
