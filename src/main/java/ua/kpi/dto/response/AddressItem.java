package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class AddressItem {
    private String city;
    private String street;
    private String homeNumber;
    private String apartmentNumber;
}
