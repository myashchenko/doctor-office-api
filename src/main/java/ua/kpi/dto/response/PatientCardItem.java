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
public class PatientCardItem extends ResourceSupport {
    @JsonProperty("id")
    private String cardId;
    private String date;
    private String workplace;
    private String complaint;
    private String diagnosis;
    private String relatedDiseases;
    private String visualInspection;
}
