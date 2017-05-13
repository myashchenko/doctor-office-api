package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class EventItem {
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private String color;
}
