package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.messageDto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto save(MessageDto messageDto);
    MessageDto update(Long id, MessageDto messageDto);
    MessageDto findById(Long id);
    List<MessageDto> findAll();
    void deleteById(Long id);
}
