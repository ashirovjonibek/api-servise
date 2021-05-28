package servise_2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servise_2.demo.entity.Credit;

public interface CreditRepository extends JpaRepository<Credit,Integer> {
}
