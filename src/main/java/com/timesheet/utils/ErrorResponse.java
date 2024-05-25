package com.timesheet.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
