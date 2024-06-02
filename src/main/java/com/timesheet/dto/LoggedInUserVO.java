package com.timesheet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timesheet.config.MaUserDetails;
import com.timesheet.entity.MaCompany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoggedInUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3205717675460229264L;
    private Long userId;
    private String jwtToken;
    private String name;
    private String email;
    private MaCompany maCompany;
    private List<String> roles;

}
