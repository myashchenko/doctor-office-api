package ua.kpi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreatePassportRequest {
    @NotBlank
    private String series;
    @NotNull
    private Integer number;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;
    @NotNull
    private String where;
}
