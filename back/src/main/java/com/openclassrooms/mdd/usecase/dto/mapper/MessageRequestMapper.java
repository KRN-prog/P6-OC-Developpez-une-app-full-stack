package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.MessageEntity;
import com.openclassrooms.mdd.usecase.dto.request.MessageRequestDto;

public class MessageRequestMapper {

    public static MessageEntity maptoMessageEntity(MessageRequestDto messageRequestDto) {
        return MessageEntity.builder()
                .message(messageRequestDto.getMessage())
                .article(messageRequestDto.getArticleId())
                .user(messageRequestDto.getUserId())
                .build();
    }

}
