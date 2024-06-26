package com.openclassrooms.mdd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.MessageEntity;
import com.openclassrooms.mdd.repository.MessageRepository;
import com.openclassrooms.mdd.usecase.dto.MessageDto;
import com.openclassrooms.mdd.usecase.dto.mapper.MessageMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.MessageRequestMapper;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    /**
     * Pemret de poster un nouveau message
     * 
     * @param messageRequestDto
     * @return un message poster en base de données
     */
    @Transactional
    public MessageDto postNewMessage(MessageRequestDto messageRequestDto) {
        MessageEntity message = MessageRequestMapper.maptoMessageEntity(messageRequestDto);
        messageRepository.save(message);

        return MessageMapper.maptoMessageDto(message);
    }

    /**
     * Permet de retrouver tout les messages posté sur un article
     * 
     * @param idArticle
     * @return une liste de message poster sur un article
     */
    public List<MessageDto> getMessageFromArticle(int idArticle) {

        List<MessageEntity> message = messageRepository.findByArticle_ArticleId(idArticle);

        if (message == null) {
            return null;
        }

        List<MessageDto> themesDtos = message.stream()
                .map(MessageMapper::maptoMessageDto)
                .collect(Collectors.toList());

        return themesDtos;

    }

}
