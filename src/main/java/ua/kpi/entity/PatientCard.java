package ua.kpi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@Entity
@Table(name = "patient_card")
public class PatientCard extends BaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "workplace")
    private String workplace;
    @Column(name = "complaint")
    private String complaint;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "related_diseases")
    private String relatedDiseases;
    @Column(name = "visual_inspection")
    private String visualInspection;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
