package ua.kpi.controller;

import io.github.yashchenkon.test.JsonContentLoader;
import io.github.yashchenkon.test.matcher.JsonMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.kpi.DoctorOfficeApplication;

import static org.junit.Assert.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
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
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PatientCardControllerTest {

    private static final String PATIENT_CARD_LIST = "cards-list";
    private static final String PATIENT_CARD_DETAILS = "cards-details";
    private static final String PATIENT_CARD_CREATE = "cards-create";

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JsonContentLoader jsonContentLoader = new JsonContentLoader();

    @Test
    public void shouldReturnPatientCardList() throws Exception {
        String responseBody = mockMvc.perform(get("/cards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isFound())
                .andDo(document(PATIENT_CARD_LIST))
                .andReturn().getResponse().getContentAsString();
        assertThat(responseBody, JsonMatcher.json(jsonContentLoader.load()));
    }

    @Test
    public void shouldReturnPatientCardDetails() throws Exception {
        String responseBody = mockMvc.perform(get("/cards/1/details")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isFound())
                .andDo(document(PATIENT_CARD_DETAILS))
                .andReturn().getResponse().getContentAsString();
        assertThat(responseBody, JsonMatcher.json(jsonContentLoader.load()));
    }

    @Test
    public void shouldReturnNotFoundIfPatientCardDoesNotExist() throws Exception {
        mockMvc.perform(get("/cards/NOT_EXISTED/details")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreatePatientCard() throws Exception {
        mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isCreated())
                .andDo(document(PATIENT_CARD_CREATE));
    }

    @Test
    public void shouldNotCreatePatientCardIfPatientDoesNotExist() throws Exception {
        mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("test", "test"))
                .content(jsonContentLoader.load("request")))
                .andExpect(status().isBadRequest());
    }
}
