package com.timesheet.service;

import com.timesheet.repository.ProjectRepository;
import com.timesheet.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
}
