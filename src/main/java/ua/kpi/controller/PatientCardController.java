package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreatePatientCardRequest;
import ua.kpi.dto.response.PatientCardItem;
import ua.kpi.entity.PatientCard;
import ua.kpi.exception.EntityNotFoundException;
import ua.kpi.repository.PatientCardRepository;
import ua.kpi.repository.PatientRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mykola Yashchenko
 */
@RestController
@RequestMapping(value = "cards")
@AllArgsConstructor
public class PatientCardController {

    private final PatientRepository patientRepository;
    private final PatientCardRepository patientCardRepository;
    private final MapperFacade mapperFacade;

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{id}")
    public List<PatientCardItem> getList(@PathVariable("id") String patientId) {
        return patientCardRepository.findAllByPatientIdOrderByDate(patientId).stream()
                .map(p -> {
                    PatientCardItem patientListItem = new PatientCardItem();
                    patientListItem.setId(p.getId());
                    patientListItem.setDate(p.getDate().toString());
                    return patientListItem;
                }).collect(toList());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/details")
    public PatientCardItem get(@PathVariable("id") String id) {
        PatientCard patientCard = patientCardRepository.getOne(id);
        if (patientCard == null) {
            throw new EntityNotFoundException("Patient card with id = %s doesn't exist", id);
        }

        return mapperFacade.map(patientCard, PatientCardItem.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody CreatePatientCardRequest createPatientCardRequest) {
        PatientCard patientCard = mapperFacade.map(createPatientCardRequest, PatientCard.class);
        patientCard.setDate(LocalDate.now());
        patientCard.setPatient(patientRepository.findOne(createPatientCardRequest.getPatientId()));
        patientCardRepository.save(patientCard);
    }
}
