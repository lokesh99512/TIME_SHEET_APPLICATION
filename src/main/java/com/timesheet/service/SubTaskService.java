package com.timesheet.service;

import com.timesheet.repository.SubTaskRepository;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubTaskService {

    @Autowired
    private SubTaskRepository subTaskRepository;
}
