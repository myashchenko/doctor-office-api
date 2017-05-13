package ua.kpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.entity.Event;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mykola Yashchenko
 */
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByDateAndDoctorEmail(LocalDate date, String email);
}
