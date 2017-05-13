package ua.kpi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends BaseEntity {
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "home_number", nullable = false)
    private String homeNumber;
    @Column(name = "apartment_number")
    private String apartmentNumber;
}
