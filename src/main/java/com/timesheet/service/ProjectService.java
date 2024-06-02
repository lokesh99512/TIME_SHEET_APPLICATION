package com.timesheet.service;

import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.MaUser;
import com.timesheet.entity.Project;
import com.timesheet.entity.ProjectUser;
import com.timesheet.repository.ProjectRepository;
import com.timesheet.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MaUserService maUserService;


    @Transactional
    public Page<Project> getAllProjects(MaUserDetails userDetails, Pageable pageable) {
        return projectRepository.findAllProjectsByCompanyIdAndUserId(userDetails.getMaCompany().getId(),userDetails.getId(),pageable);
    }

    @Transactional
    public Project saveProject(Project project, MaUserDetails userDetails) {
        project.getProjectUsers().forEach(user->{
            user.setProject(project);
        });
        project.setMaCompany(userDetails.getMaCompany());
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProjectUsersByProjectId(Long id){
        projectRepository.deleteProjectUsersByProjectId(id);
    }
}
