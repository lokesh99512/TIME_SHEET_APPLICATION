package com.timesheet.controller;


import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.MaUser;
import com.timesheet.entity.Project;
import com.timesheet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<Page<Project>> getMaUser(@PageableDefault(size = Integer.MAX_VALUE, page = 0, direction = Sort.Direction.DESC, sort = {"id"}) Pageable pageable){
        MaUserDetails userDetails = (MaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Page<Project>>(projectService.getAllProjects(userDetails, pageable ), HttpStatus.OK);
    }

    @RequestMapping(path="/" , method = RequestMethod.POST)
    public ResponseEntity<Project> save(@RequestBody Project project){
        MaUserDetails userDetails = (MaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(project.getId()!=null)
          projectService.deleteProjectUsersByProjectId(project.getId());
        return new ResponseEntity<Project>(projectService.saveProject(project,userDetails), HttpStatus.OK);
    }
}
