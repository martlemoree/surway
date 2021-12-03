package htw.berlin.webtech.surway.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    List<SurveyEntity> findAllByTitle(String title);

}
