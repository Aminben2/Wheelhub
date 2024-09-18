package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.messageDto.MessageDto;
import com.WheelHub.WheelHub.entity.Message;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public static MessageDto entityToDTO(Message message) {
    return MessageDto.builder()
        .senderId(message.getSender() != null ? message.getSender().getId() : null)
        .receiverId(message.getReceiver() != null ? message.getReceiver().getId() : null)
        .messageContent(message.getMessageContent())
        .sentAt(message.getSentAt())
        .build();
  }

  public static Message dtoToEntity(MessageDto messageDto) {
    Message message =
        Message.builder()
            .messageContent(messageDto.getMessageContent())
            .sentAt(messageDto.getSentAt())
            .build();

    if (messageDto.getSenderId() != null) {
      User sender = new User();
      sender.setId(messageDto.getSenderId());
      message.setSender(sender);
    }
    if (messageDto.getReceiverId() != null) {
      User receiver = new User();
      receiver.setId(messageDto.getReceiverId());
      message.setReceiver(receiver);
    }

    return message;
  }
}
