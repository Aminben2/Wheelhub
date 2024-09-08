package com.WheelHub.WheelHub.dto.messageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageDto {
    private Long senderId;
    private Long receiverId;
    private String messageContent;
    private LocalDateTime sentAt;
}
