package service_1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service_1.demo.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByPassport(String passport);
}
