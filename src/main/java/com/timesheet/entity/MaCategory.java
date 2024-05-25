package com.timesheet.entity;

import com.timesheet.utils.BaseEntity;
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
@Table(name="ma_category",
              indexes = {
              @Index(name="ma_category_company_index", columnList = "ma_company_id")
       })
public class MaCategory extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ma_company_id", nullable = true, referencedColumnName = "id")
    private MaCompany maCompany;
}
