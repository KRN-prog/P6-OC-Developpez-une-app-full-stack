package com.openclassrooms.mdd.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.MessageService;
import com.openclassrooms.mdd.usecase.dto.MessageDto;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageUseCase {

    private final MessageService messageService;

    public MessageDto postNewMessage(MessageRequestDto messageRequestDto) {
        return messageService.postNewMessage(messageRequestDto);
    }

    public List<MessageDto> getMessageFromArticle(int idArticle) {
        List<MessageDto> messageDtos = messageService.getMessageFromArticle(idArticle);

        if (messageDtos == null) {
            return null;
        }
        return messageDtos;
    }

}
