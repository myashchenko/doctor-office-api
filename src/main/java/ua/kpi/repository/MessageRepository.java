package ua.kpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.entity.Message;

/**
 * @author Mykola Yashchenko
 */
public interface MessageRepository extends JpaRepository<Message, String> {

}