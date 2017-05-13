package ua.kpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.entity.User;

/**
 * @author Mykola Yashchenko
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
