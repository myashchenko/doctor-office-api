package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreateAddressRequest {
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String homeNumber;
    private String apartmentNumber;
}
