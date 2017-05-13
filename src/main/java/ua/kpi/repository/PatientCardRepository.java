package ua.kpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.entity.PatientCard;

import java.util.List;

/**
 * @author Mykola Yashchenko
 */
public interface PatientCardRepository extends JpaRepository<PatientCard, String> {
    List<PatientCard> findAllByPatientIdOrderByDate(String patientId);
}
