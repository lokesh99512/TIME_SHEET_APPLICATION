package com.timesheet.service;

import com.timesheet.repository.SubTaskRepository;
import com.timesheet.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
}