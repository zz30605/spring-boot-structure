package spring.boot.structure.config.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "rule")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Rule {
    //define a form to create database table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String company;

    private String brand;

    private String platform;

    private String locale;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "datetime(0)")
    @CreatedDate
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false, columnDefinition = "datetime(0)")
    @LastModifiedDate
    private Date updateDate;


}
