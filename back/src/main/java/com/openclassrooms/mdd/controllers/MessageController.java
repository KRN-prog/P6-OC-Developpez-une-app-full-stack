package com.openclassrooms.mdd.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.MessageUseCase;
import com.openclassrooms.mdd.usecase.dto.MessageDto;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

@RestController
@RequestMapping("/mdd")
public class MessageController {

    private final MessageUseCase messageUseCase;

    public MessageController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    /**
     * Permet de poster un message
     * 
     * @param messageRequestDto
     * @return un message enregistrer en base de données
     */
    @PostMapping("/post/message")
    public ResponseEntity<?> postNewMessage(@Valid @RequestBody MessageRequestDto messageRequestDto) {
        MessageDto messageDto = this.messageUseCase.postNewMessage(messageRequestDto);
        if (messageDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Message error: Cannot post comment");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(messageDto);
    }

    /**
     * Retrouver un message via son id poster en base de données
     * 
     * @param idArticle
     * @return un message qui à été posté en base de données via son id
     */
    @GetMapping("/get/message/{id}")
    public ResponseEntity<?> getMessageFromArticle(@Valid @PathVariable("id") Integer idArticle) {
        List<MessageDto> messageDtos = this.messageUseCase.getMessageFromArticle(idArticle);
        if (messageDtos == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Aucun message trouvé");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(messageDtos);
    }

}
