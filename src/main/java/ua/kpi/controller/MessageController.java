package ua.kpi.controller;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ua.kpi.dto.request.SendMessageRequest;
import ua.kpi.dto.response.MessageItem;
import ua.kpi.entity.Message;
import ua.kpi.repository.MessageRepository;
import ua.kpi.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mykola Yashchenko
 */
@Controller
@AllArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MapperFacade mapperFacade;

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public List<MessageItem> getMessages() {
        return messageRepository.findAll().stream()
                .map(this::toMessageItem)
                .collect(Collectors.toList());
    }

    @MessageMapping("/messages/send")
    @SendTo("/topic/messages")
    public List<MessageItem> sendMessage(@Payload SendMessageRequest sendMessageRequest, Principal principal) {
        Message message = mapperFacade.map(sendMessageRequest, Message.class);
        message.setTime(LocalDateTime.now());
        message.setDoctor(userRepository.findByEmail(principal.getName()));

        messageRepository.save(message);

        return Collections.singletonList(toMessageItem(message));
    }

    private MessageItem toMessageItem(Message message) {
        MessageItem messageItem = new MessageItem();
        messageItem.setText(message.getText());
        messageItem.setTime(message.getTime().toString());
        messageItem.setDoctorName(message.getDoctor().getEmail());
        return messageItem;
    }
}
