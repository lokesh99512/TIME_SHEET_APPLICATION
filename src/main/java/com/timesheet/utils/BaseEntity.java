package com.timesheet.utils;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseEntity implements Serializable {

    @Column(name = "created_date", nullable = true, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = true)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by", nullable = true)
    @LastModifiedBy
    private Long modifiedBy;

    @Column(name = "created_by", nullable = true, updatable = false)
    @CreatedBy
    private Long createdBy;

}