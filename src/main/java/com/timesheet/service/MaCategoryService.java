package com.timesheet.service;

import com.timesheet.repository.MaCategoryRepository;
import com.timesheet.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaCategoryService {

    @Autowired
    private MaCategoryRepository maCategoryRepository;
}
