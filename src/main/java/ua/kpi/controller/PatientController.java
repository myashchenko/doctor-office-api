package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreatePatientRequest;
import ua.kpi.dto.response.PatientDetailItem;
import ua.kpi.dto.response.PatientListItem;
import ua.kpi.entity.Patient;
import ua.kpi.repository.PatientRepository;
import ua.kpi.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
                    patientListItem.setPatientId(p.getId());
                    patientListItem.setFirstName(p.getFirstName());
                    patientListItem.setLastName(p.getLastName());
                    patientListItem.add(linkTo(methodOn(PatientController.class).get(p.getId(), principal)).withRel("details"));
                    return patientListItem;
                }).collect(toList());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public PatientDetailItem get(@PathVariable("id") String id, Principal principal) {
        Patient patientProjection = patientRepository.getByIdAndDoctorEmail(id, principal.getName());

        PatientDetailItem patientDetails = mapperFacade.map(patientProjection, PatientDetailItem.class);
        patientDetails.setPatientId(id);

        patientDetails.add(linkTo(methodOn(PatientController.class).get(id, principal)).withSelfRel());
        patientDetails.add(linkTo(methodOn(PatientCardController.class).getList(id)).withRel("card"));

        return patientDetails;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody CreatePatientRequest createPatientRequest, Principal principal) {
        Patient patient = mapperFacade.map(createPatientRequest, Patient.class);
        patient.setDoctor(userRepository.findByEmail(principal.getName()));

        patientRepository.save(patient);
    }
}
