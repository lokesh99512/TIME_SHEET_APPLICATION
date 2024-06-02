package com.timesheet.config;

import com.timesheet.entity.MaCompany;
import com.timesheet.entity.MaUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MaUserDetails implements UserDetails {


    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    private String name;
    private Long id;
    private MaCompany maCompany;
    private MaUser maUser;

    public MaUserDetails(MaUser maUser){
        email=maUser.getEmail();
        password=maUser.getPassword();
        name=maUser.getFirstName()+" "+maUser.getLastName();
        id=maUser.getId();
        this.maUser=maUser;
        maCompany=maUser.getMaCompany();
        authorities= Arrays.stream(maUser.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
