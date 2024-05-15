package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.MessageEntity;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.MessageRepository;
import com.openclassrooms.mdd.usecase.dto.MessageDto;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.mapper.MessageMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.MessageRequestMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
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

    public ResponseEntity<?> getMessageFromArticle(int idArticle) {

        List<MessageEntity> message = messageRepository.findAllByMessageId(idArticle);
        
        if (message != null) {
            List<MessageDto> themesDtos = message.stream()
                    .map(MessageMapper::maptoMessageDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(themesDtos);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Aucun message trouvé");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
