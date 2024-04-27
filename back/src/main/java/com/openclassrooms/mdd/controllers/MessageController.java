package com.openclassrooms.mdd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.MessageUseCase;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

@RestController
@RequestMapping("/mdd")
public class MessageController {

    private final MessageUseCase messageUseCase;

    public MessageController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    @PostMapping("/post/message")
    public ResponseEntity<?> postNewMessage(@Valid @RequestBody MessageRequestDto messageRequestDto) {
        return this.messageUseCase.postNewMessage(messageRequestDto);
    }

}
