package com.timesheet.entity;

import com.timesheet.utils.BaseEntity;
import com.timesheet.utils.Status;
import jakarta.persistence.*;
import lombok.*;
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
public class MaCompany extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    private String logo;
    private String phoneNumber;
    private String email;
    private String website;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
