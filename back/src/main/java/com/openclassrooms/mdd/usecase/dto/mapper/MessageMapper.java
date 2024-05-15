package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.MessageEntity;
import com.openclassrooms.mdd.usecase.dto.MessageDto;

public class MessageMapper {
    
    public static MessageDto maptoMessageDto(MessageEntity messageEntity) {
        return new MessageDto(
                messageEntity.getMessageId(),
                messageEntity.getMessage(),
                messageEntity.getArticle(),
                messageEntity.getUser());
    }

    public static MessageEntity maptoMessageEntity(MessageDto messageDto) {
        return new MessageEntity(
            messageDto.getId(),
            messageDto.getMessage(),
            messageDto.getIdArticle(),
            messageDto.getIdUser());
    }

}
