package com.timesheet.config;

import com.timesheet.entity.MaUser;
import com.timesheet.repository.MaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MaUserDetailsService implements UserDetailsService {

    @Autowired
    private MaUserRepository maUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MaUser> maUser= maUserRepository.findByEmail(username);
       return maUser.map(MaUserDetails::new).
                orElseThrow(()-> new UsernameNotFoundException("User not found"+ username));
    }
}
