package com.timesheet.controller;
import com.timesheet.config.MaUserDetails;
import com.timesheet.config.jwt.JwtService;
import com.timesheet.dto.AuthRequest;
import com.timesheet.dto.LoggedInUserVO;
import com.timesheet.entity.MaUser;
import com.timesheet.service.MaUserService;
import com.timesheet.utils.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
public class MaUserController {

    @Autowired
    private MaUserService maUserService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<Page<MaUser>> getMaUser( @PageableDefault(size = Integer.MAX_VALUE, page = 0, direction = Sort.Direction.DESC, sort = {"id"}) Pageable pageable){
        MaUserDetails userDetails = (MaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Page<MaUser>>(maUserService.getAllUser(userDetails, pageable ), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MaUser> getMaUserById(@PathVariable Long id){
        return new ResponseEntity<MaUser>(maUserService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(path="/sign-up" , method = RequestMethod.POST)
    public ResponseEntity<MaUser> saveUser(@RequestBody MaUser maUser){
        MaUserDetails userDetails = (MaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<MaUser>(maUserService.saveUser(maUser,userDetails), HttpStatus.OK);
    }

    @RequestMapping(path="/sign-in" ,produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<LoggedInUserVO> signIn(@RequestHeader(name = "email", required = true) String email, @RequestHeader(name = "password", required = true) String password, @RequestHeader(name = "remember-me", required = true) Boolean rememberMe) {
        try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        if (authentication.isAuthenticated()) {
            MaUserDetails userDetails = (MaUserDetails) authentication.getPrincipal();
            LoggedInUserVO loggedInUser = new LoggedInUserVO();
            loggedInUser.setUserId(userDetails.getId());
            loggedInUser.setJwtToken(jwtService.generateToken(email));
            loggedInUser.setName(userDetails.getName());
            loggedInUser.setEmail(userDetails.getEmail());
            loggedInUser.setMaCompany(userDetails.getMaCompany());
            loggedInUser.setRoles(userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(loggedInUser);
        } else {
            throw new UsernameNotFoundException("Invalid Details");
        }
        }catch (UsernameNotFoundException e){
            throw new AppException("User is not exits in db please check.");
        }catch (Exception e){
            throw new AppException("Invalid Details");
        }
    }

}
