package com.timesheet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timesheet.utils.BaseEntity;
import com.timesheet.utils.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@Table(name="ma_user",
        indexes = {
                @Index(name="ma_user_company_index", columnList = "ma_company_id")
        })
public class MaUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_company_id", nullable = true, referencedColumnName = "id")
    private MaCompany maCompany;

    @Column(name="email", unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private String logo;

    private String roles;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

}
