package com.timesheet.service;

import com.timesheet.repository.MaCompanyRepository;
import com.timesheet.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaCompanyService {

    @Autowired
    private MaCompanyRepository maCompanyRepository;
}
