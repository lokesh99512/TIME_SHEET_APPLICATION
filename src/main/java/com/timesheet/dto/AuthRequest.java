package com.timesheet.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}
