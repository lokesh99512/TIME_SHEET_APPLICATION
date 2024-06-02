package com.timesheet.controller;


import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.Project;
import com.timesheet.entity.Task;
import com.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(path="/" , method = RequestMethod.POST)
    public ResponseEntity<Task> save(@RequestBody Task task){
        MaUserDetails userDetails = (MaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Task>(taskService.saveTask(task,userDetails), HttpStatus.OK);
    }

}
