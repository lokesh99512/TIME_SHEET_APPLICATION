package com.timesheet.entity;

import com.timesheet.utils.BaseEntity;
import com.timesheet.utils.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Audited
@Table(name="project",
        indexes = {
                @Index(name="project_company_index", columnList = "ma_company_id")
        })
public class Project extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true)
    private String name;
    private String description;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectUser> projectUsers;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_company_id", nullable = true, referencedColumnName = "id")
    private MaCompany maCompany;

    private Boolean isBillable;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
