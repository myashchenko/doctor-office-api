package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreatePatientCardRequest {
    @NotBlank
    private String workplace;
    @NotBlank
    private String complaint;
    @NotBlank
    private String diagnosis;
    @NotBlank
    private String relatedDiseases;
    @NotBlank
    private String visualInspection;
    @NotBlank
    private String patientId;
}
