package com.timesheet.entity;

import com.timesheet.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Audited
@Table(name="time_log_detail",
        indexes = {
                @Index(name="time_log_detail_company_index", columnList = "ma_company_id"),
                @Index(name="time_log_detail_user_index",columnList = "ma_user_id")
        })
public class TimeLogDetail extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime timeLogDate;
    private LocalTime timeLogTime;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_company_id", nullable = true, referencedColumnName = "id")
    private MaCompany maCompany;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_user_id", nullable = true, referencedColumnName = "id")
    private MaUser maUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "task_id", nullable = false, referencedColumnName = "id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sub_task_id", nullable = false, referencedColumnName = "id")
    private SubTask subTask;

}
