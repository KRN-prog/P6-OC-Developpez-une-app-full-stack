package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.MessageService;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageUseCase {

    private final MessageService messageService;

    public ResponseEntity<?> postNewMessage(MessageRequestDto messageRequestDto) {
        return messageService.postNewMessage(messageRequestDto);
    }

    public ResponseEntity<?> getMessageFromArticle(int idArticle) {
        return messageService.getMessageFromArticle(idArticle);
    }

}
