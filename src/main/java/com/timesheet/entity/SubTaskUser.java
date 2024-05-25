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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Audited
public class SubTaskUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_user_id", nullable = true, referencedColumnName = "id")
    private MaUser maUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sub_task_id", nullable = false, referencedColumnName = "id")
    private SubTask subTask;
}
