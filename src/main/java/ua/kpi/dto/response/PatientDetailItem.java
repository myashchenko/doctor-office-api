package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class PatientDetailItem extends PatientListItem {
    private String dateOfBirth;
    private String gender;
    private AddressItem address;
    private PassportItem passport;
}
