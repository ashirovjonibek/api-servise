package servise_2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servise_2.demo.entity.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
