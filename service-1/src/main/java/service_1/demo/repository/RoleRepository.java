package service_1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service_1.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
