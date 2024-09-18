package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.messageDto.MessageDto;
import com.WheelHub.WheelHub.service.MessageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Validated
public class MessageController {

  private final MessageService messageService;

  @PostMapping("/")
  public ResponseEntity<MessageDto> createMessage(@Valid @RequestBody MessageDto messageDto) {
    MessageDto createdMessage = messageService.save(messageDto);
    return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MessageDto> getMessageById(@PathVariable @Min(1) Long id) {
    MessageDto messageDto = messageService.findById(id);
    return new ResponseEntity<>(messageDto, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<MessageDto>> getAllMessages() {
    List<MessageDto> messages = messageService.findAll();
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MessageDto> updateMessage(
      @PathVariable @Min(1) Long id, @Valid @RequestBody MessageDto messageDto) {
    MessageDto updatedMessage = messageService.update(id, messageDto);
    return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMessage(@PathVariable @Min(1) Long id) {
    messageService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
