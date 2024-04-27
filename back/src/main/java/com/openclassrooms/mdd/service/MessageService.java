package com.openclassrooms.mdd.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.MessageEntity;
import com.openclassrooms.mdd.repository.MessageRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.MessageRequestMapper;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public ResponseEntity<?> postNewMessage(MessageRequestDto messageRequestDto) {

        MessageEntity message = MessageRequestMapper.maptoMessageEntity(messageRequestDto);

        return ResponseEntity.ok(messageRepository.save(message));

    }

}
