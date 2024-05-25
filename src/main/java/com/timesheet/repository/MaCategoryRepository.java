package com.timesheet.repository;

import com.timesheet.entity.MaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaCategoryRepository extends JpaRepository<MaCategory ,Long> {
}
