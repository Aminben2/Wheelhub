package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.messageDto.MessageDto;
import com.WheelHub.WheelHub.entity.Message;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.mapper.MessageMapper;
import com.WheelHub.WheelHub.repository.MessageRepository;
import com.WheelHub.WheelHub.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public MessageDto save(MessageDto messageDto) {
        Message message = MessageMapper.dtoToEntity(messageDto);
        message = messageRepository.save(message);
        return MessageMapper.entityToDTO(message);
    }

    @Override
    @Transactional
    public MessageDto update(Long id, MessageDto messageDto) {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found for id: " + id));

        // Update fields as necessary
        if (messageDto.getSenderId() != null) {
            User sender = new User();
            sender.setId(messageDto.getSenderId());
            existingMessage.setSender(sender);
        }
        if (messageDto.getReceiverId() != null) {
            User receiver = new User();
            receiver.setId(messageDto.getReceiverId());
            existingMessage.setReceiver(receiver);
        }
        existingMessage.setMessageContent(messageDto.getMessageContent());
        existingMessage.setSentAt(messageDto.getSentAt());

        Message updatedMessage = messageRepository.save(existingMessage);
        return MessageMapper.entityToDTO(updatedMessage);
    }


    @Override
    public MessageDto findById(Long id) {
        return messageRepository.findById(id)
                .map(MessageMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Message not found for id: " + id));
    }

    @Override
    public List<MessageDto> findAll() {
        return messageRepository.findAll().stream()
                .map(MessageMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found for id: " + id));
        messageRepository.delete(message);
    }
}
