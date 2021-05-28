package servise_2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servise_2.demo.entity.Client;
import servise_2.demo.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey,Integer> {
    Survey findByClient(Client client);
}
