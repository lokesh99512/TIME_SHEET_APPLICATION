package com.timesheet.service;

import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.Project;
import com.timesheet.entity.Task;
import com.timesheet.repository.SubTaskRepository;
import com.timesheet.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task saveTask(Task task, MaUserDetails userDetails) {
        if(task.getId()!=null)
            deleteTaskUsersByTaskId(task.getId());
        task.getTaskUsers().forEach(user->{
            user.setTask(task);
        });
        task.getSubTasks().forEach(subTask -> {
            subTask.setTask(task);
        });
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTaskUsersByTaskId(Long taskId) {
        taskRepository.deleteProjectUsersByProjectId(taskId);
    }
}
