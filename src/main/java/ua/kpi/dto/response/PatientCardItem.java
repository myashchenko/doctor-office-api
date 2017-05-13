package ua.kpi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class PatientCardItem {
    private String id;
    private String date;
    private String workplace;
    private String complaint;
    private String diagnosis;
    private String relatedDiseases;
    private String visualInspection;
}
