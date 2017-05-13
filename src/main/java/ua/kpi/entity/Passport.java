package ua.kpi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@Entity
@Table(name = "passport")
public class Passport extends BaseEntity {
    @Column(name = "series", nullable = false)
    private String series;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "where_", nullable = false)
    private String where;
}
