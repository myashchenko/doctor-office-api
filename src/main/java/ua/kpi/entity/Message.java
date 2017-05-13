package ua.kpi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message extends BaseEntity {
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "time", nullable = false)
    private LocalDateTime time;
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false, updatable = false)
    private User doctor;
}
