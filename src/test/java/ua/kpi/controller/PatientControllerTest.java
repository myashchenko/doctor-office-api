package ua.kpi.controller;

import io.github.yashchenkon.test.JsonContentLoader;
import io.github.yashchenkon.test.matcher.JsonMatcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.kpi.DoctorOfficeApplication;

import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mykola Yashchenko
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = DoctorOfficeApplication.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JsonContentLoader jsonContentLoader = new JsonContentLoader();

    @Test
    public void shouldReturnPatientList() throws Exception {
        String responseBody = mockMvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isFound())
                .andReturn().getResponse().getContentAsString();
        assertThat(responseBody, JsonMatcher.json(jsonContentLoader.load()));
    }

    @Test
    public void shouldReturnPatientDetails() throws Exception {
        String responseBody = mockMvc.perform(get("/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isFound())
                .andReturn().getResponse().getContentAsString();
        assertThat(responseBody, JsonMatcher.json(jsonContentLoader.load()));
    }

    @Test
    public void shouldReturnNotFoundIfPatientDoesNotExist() throws Exception {
        mockMvc.perform(get("/patients/NOT_EXISTED")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreatePatient() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreatePatientIfFirstNameIsBlank() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreatePatientIfLastNameIsBlank() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Ignore("need to fix date validation")
    public void shouldNotCreatePatientIfDateFormatIsIncorrect() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreatePatientIfGenderIsIncorrect() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreatePatientIfPassportIsNull() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreatePatientIfAddressIsNull() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }
}
