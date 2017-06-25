package ua.kpi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.kpi.dto.request.CreateUserRequest;
import ua.kpi.dto.response.CurrentUser;
import ua.kpi.entity.User;
import ua.kpi.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Mykola Yashchenko
 */
@RestController
@RequestMapping(value = "users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public CurrentUser me(Principal principal) {
        CurrentUser currentUser = new CurrentUser(principal.getName());

        currentUser.add(linkTo(methodOn(UserController.class).me(principal)).withSelfRel());
        currentUser.add(linkTo(
                methodOn(EventController.class).findByDate(LocalDate.now().toString(), principal)).withRel("events"));
        currentUser.add(linkTo(methodOn(MessageController.class).getMessages()).withRel("messages"));

        return currentUser;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

    }
}
