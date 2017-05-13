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
@Table(name = "event")
public class Event extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "start_time", nullable = false)
    private String startTime;
    @Column(name = "end_time", nullable = false)
    private String endTime;
    @Column(name = "color", nullable = false)
    private String color;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;
}
