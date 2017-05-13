package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private String password;
}
