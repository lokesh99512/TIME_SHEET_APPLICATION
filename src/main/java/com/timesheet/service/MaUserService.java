package com.timesheet.service;

import com.timesheet.entity.MaUser;
import com.timesheet.repository.MaUserRepository;
import com.timesheet.repository.SubTaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaUserService {

    @Autowired
    private MaUserRepository maUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public List<MaUser> getAllUser(){
        return maUserRepository.findAll();
    }

    @Transactional
    public MaUser getById(Long id){
        return maUserRepository.findById(id).get();
    }

    @Transactional
    public MaUser saveUser(MaUser maUser) {
        maUser.setPassword(passwordEncoder.encode(maUser.getPassword()));
        return maUserRepository.save(maUser);
    }
}
