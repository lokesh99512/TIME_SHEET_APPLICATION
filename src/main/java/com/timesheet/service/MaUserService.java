package com.timesheet.service;

import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.MaUser;
import com.timesheet.repository.MaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaUserService {

    @Autowired
    private MaUserRepository maUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static Long modifiedBy;
    public static Long createdBy;

    @Transactional
    public Page<MaUser> getAllUser(MaUserDetails maUserDetails, Pageable pageble){
        return maUserRepository.findAllUserByCompanyId(maUserDetails.getMaCompany().getId(), pageble);
    }

    @Transactional
    public MaUser getById(Long id){
        return maUserRepository.findById(id).get();
    }

    @Transactional
    public MaUser saveUser(MaUser maUser,MaUserDetails maUserDetails) {
        maUser.setMaCompany(maUserDetails.getMaCompany());
        maUser.setPassword(passwordEncoder.encode(maUser.getPassword()));
        return maUserRepository.save(maUser);
    }

    public void setUpdatedByAndModifiedBY(MaUserDetails userDetails){
        modifiedBy=userDetails.getId();
        createdBy=userDetails.getId();
    }

    @Transactional
    public List<MaUser> findAllUserByRoleAdmin(Long companyId){
        return maUserRepository.findAllUserByRoleAdmin(companyId);
    }
}
