package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreatePatientRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dateOfBirth;
    @NotBlank
    @Pattern(regexp = "MALE|FEMALE", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String gender;
    @Valid
    @NotNull
    private CreatePassportRequest passport;
    @Valid
    @NotNull
    private CreateAddressRequest address;
}
