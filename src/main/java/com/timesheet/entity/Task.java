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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Audited
@Table(name="task",
        indexes = {
                @Index(name="task_company_index", columnList = "ma_company_id"),
                @Index(name="task_category_index",columnList = "ma_category_id")
        })
public class Task extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true)
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "project_id", nullable = false, referencedColumnName = "id")
    private Project project;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTasks;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_company_id", nullable = true, referencedColumnName = "id")
    private MaCompany maCompany;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_category_id", nullable = true, referencedColumnName = "id")
    private MaCategory maCategory;

}
