package ua.kpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.entity.Patient;
import ua.kpi.projection.PatientListProjection;

import java.util.List;

/**
 * @author Mykola Yashchenko
 */
public interface PatientRepository extends JpaRepository<Patient, String> {
    List<PatientListProjection> findAllByDoctorEmail(String email);

    Patient findByIdAndDoctorEmail(String id, String email);
}
