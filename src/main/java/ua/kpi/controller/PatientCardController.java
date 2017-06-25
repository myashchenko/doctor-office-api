package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreatePatientCardRequest;
import ua.kpi.dto.response.PatientCardItem;
import ua.kpi.entity.PatientCard;
import ua.kpi.repository.PatientCardRepository;
import ua.kpi.repository.PatientRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
                    patientListItem.setCardId(p.getId());
                    patientListItem.setDate(p.getDate().toString());
                    patientListItem.add(linkTo(methodOn(PatientCardController.class).get(p.getId())).withRel("details"));
                    return patientListItem;
                }).collect(toList());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/details")
    public PatientCardItem get(@PathVariable("id") String id) {
        PatientCard patientCard = patientCardRepository.getOne(id);

        PatientCardItem cardItem = mapperFacade.map(patientCard, PatientCardItem.class);
        cardItem.setCardId(patientCard.getId());

        cardItem.add(linkTo(methodOn(PatientCardController.class).get(id)).withSelfRel());

        return cardItem;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody CreatePatientCardRequest createPatientCardRequest) {
        PatientCard patientCard = mapperFacade.map(createPatientCardRequest, PatientCard.class);
        patientCard.setDate(LocalDate.now());
        patientCard.setPatient(patientRepository.getOne(createPatientCardRequest.getPatientId()));
        patientCardRepository.save(patientCard);
    }
}
