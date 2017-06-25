package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreateEventRequest;
import ua.kpi.dto.response.EventItem;
import ua.kpi.entity.Event;
import ua.kpi.repository.EventRepository;
import ua.kpi.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mykola Yashchenko
 */
@RestController
@RequestMapping(value = "events")
@AllArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final MapperFacade mapperFacade;

    @GetMapping
    public List<EventItem> findByDate(@RequestParam("date") String date, Principal principal) {
        LocalDate localDate = LocalDate.parse(date);

        return eventRepository.findAllByDateAndDoctorEmail(localDate, principal.getName()).stream()
                .map(event -> mapperFacade.map(event, EventItem.class)).collect(toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateEventRequest createEventRequest, Principal principal) {
        Event event = mapperFacade.map(createEventRequest, Event.class);
        event.setDoctor(userRepository.findByEmail(principal.getName()));

        eventRepository.save(event);
    }
}
