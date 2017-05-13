package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class PassportItem {
    private String series;
    private Integer number;
    private String date;
    private String where;
}
