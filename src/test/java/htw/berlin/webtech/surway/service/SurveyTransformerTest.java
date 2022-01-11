package htw.berlin.webtech.surway.service;

import htw.berlin.webtech.surway.persistance.Limit;
import htw.berlin.webtech.surway.persistance.SurveyEntity;
import htw.berlin.webtech.surway.persistance.SectionEntity;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.doReturn;

class SurveyTransformerTest implements WithAssertions {

    private final SurveyTransformer underTest = new SurveyTransformer();

    @Test
    @DisplayName("should transform SurveyEntity to Survey")
    void should_transform_survey_entity_to_survey() {
        // given
        var surveyEntity = Mockito.mock(SurveyEntity.class);
        doReturn(111L).when(surveyEntity).getId();
        doReturn("Test Survey").when(surveyEntity).getTitle();
        doReturn("just a mocked survey for testing").when(surveyEntity).getDescription();
        doReturn(true).when(surveyEntity).getLimited();
        doReturn(Limit.thirty).when(surveyEntity).getLimitDate();
        doReturn(List.of(new SectionEntity())).when(surveyEntity).getSections();

        // when
        var result = underTest.transformEntity(surveyEntity);

        // then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getTitle()).isEqualTo("Test Survey");
        assertThat(result.getDescription()).isEqualTo("just a mocked survey for testing");
        assertThat(result.isLimited()).isEqualTo(true);
        assertThat(result.getLimitDate()).isEqualTo("thirty");
        assertThat(result.getSections()).hasSize(1);
    }
}
