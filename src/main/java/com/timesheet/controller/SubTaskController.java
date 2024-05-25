package com.timesheet.controller;

import com.timesheet.repository.SubTaskRepository;
import com.timesheet.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subtask")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;
}
