package com.timesheet.repository;

import com.timesheet.entity.SubTask;
import com.timesheet.entity.TimeLogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLogDetailRepository extends JpaRepository<TimeLogDetail ,Long> {
}
