package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreatePatientRequest;
import ua.kpi.dto.response.PatientDetailItem;
import ua.kpi.dto.response.PatientListItem;
import ua.kpi.entity.Patient;
import ua.kpi.exception.EntityNotFoundException;
import ua.kpi.repository.PatientRepository;
import ua.kpi.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mykola Yashchenko
 */
@RestController
@RequestMapping(value = "patients")
@AllArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final MapperFacade mapperFacade;

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<PatientListItem> getList(Principal principal) {
        return patientRepository.findAllByDoctorEmail(principal.getName()).stream()
                .map(p -> {
                    PatientListItem patientListItem = new PatientListItem();
                    patientListItem.setId(p.getId());
                    patientListItem.setFirstName(p.getFirstName());
                    patientListItem.setLastName(p.getLastName());
                    return patientListItem;
                }).collect(toList());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public PatientDetailItem get(@PathVariable("id") String id, Principal principal) {
        Patient patientProjection = patientRepository.findByIdAndDoctorEmail(id, principal.getName());
        if (patientProjection == null) {
            throw new EntityNotFoundException("Patient with id = %s doesn't exist", id);
        }

        return mapperFacade.map(patientProjection, PatientDetailItem.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody CreatePatientRequest createPatientRequest, Principal principal) {
        Patient patient = mapperFacade.map(createPatientRequest, Patient.class);
        patient.setDoctor(userRepository.findByEmail(principal.getName()));

        patientRepository.save(patient);
    }
}
