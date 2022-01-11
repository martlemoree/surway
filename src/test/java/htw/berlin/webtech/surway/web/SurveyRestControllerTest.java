package htw.berlin.webtech.surway.web;

import htw.berlin.webtech.surway.service.SurveyService;
import htw.berlin.webtech.surway.web.api.Survey;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyRestController.class)
class SurveyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    @DisplayName("should return found survey from survey service")
    void should_return_found_survey_from_survey_service() throws Exception {
        // given
        var surveys = List.of(
                new Survey(1, "Test Survey", "just a mocked survey for testing", true, "thirty", Collections.emptyList()),
                new Survey(2, "Another Test", "just another mocked survey for testing", true, "seven", Collections.emptyList())
        );
        doReturn(surveys).when(surveyService).findAll();

        // when
        mockMvc.perform(get("/api/v1/persons"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Survey"))
                .andExpect(jsonPath("$[0].description").value("just a mocked survey for testing"))
                .andExpect(jsonPath("$[0].limited").value(true))
                .andExpect(jsonPath("$[0].limitDate").value("thirty"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Another Test"))
                .andExpect(jsonPath("$[1].description").value("just another mocked survey for testing"))
                .andExpect(jsonPath("$[1].limited").value(true))
                .andExpect(jsonPath("$[1].limitDate").value("seven"));
    }

    @Test
    @DisplayName("should return 404 if survey isnt found")
    void should_return_404_if_survey_isnt_found() throws Exception {
        // given
        doReturn(null).when(surveyService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/surveys/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and location header when creating a survey")
    void should_return_201_http_status_and_location_header_when_creating_a_survey() throws Exception {
        // given
        String surveyToCreateAsJson = "{\"title\": \"Test Survey\", \"description\": \"Test Description\", \"limited\": true, \"limitDate\": \"seven\"}";
        var survey = new Survey(123, null, null, false, null, null);
        doReturn(survey).when(surveyService).create(any());

        // when
        mockMvc.perform(
                post("/api/v1/surveys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(surveyToCreateAsJson)
            )
            //then
            .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/surveys/" + survey.getId()))));
    }

    @Test
    @DisplayName("should validate create survey request")
    void should_validate_create_survey_request() throws Exception {
        // given
        String surveyToCreateAsJson = "{\"title\": \"a\", \"description\": \"\", \"limited\": true, \"limitDate\": \"seven\"}";

        // when
        mockMvc.perform(
                post("/api/v1/surveys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(surveyToCreateAsJson)
            )
            // then
                .andExpect(status().isBadRequest());
    }
}
