package com.timesheet.repository;

import com.timesheet.entity.MaCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaCompanyRepository extends JpaRepository<MaCompany ,Long> {
}
