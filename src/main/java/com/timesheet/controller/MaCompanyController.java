package com.timesheet.controller;


import com.timesheet.service.MaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/company")
public class MaCompanyController {

    @Autowired
    private MaCategoryService maCategoryService;
}
