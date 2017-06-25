package ua.kpi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class PatientListItem extends ResourceSupport {
    @JsonProperty("id")
    private String patientId;
    private String firstName;
    private String lastName;
}
