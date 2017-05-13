package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class MessageItem {
    private String text;
    private String time;
    private String doctorName;
}
