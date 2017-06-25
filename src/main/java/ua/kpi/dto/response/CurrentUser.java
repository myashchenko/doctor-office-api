package ua.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@AllArgsConstructor
public class CurrentUser extends ResourceSupport {
    private String name;
}
