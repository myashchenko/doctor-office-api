package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreateEventRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @NotBlank
    private String color;
}
