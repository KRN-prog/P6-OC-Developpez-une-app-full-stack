package com.openclassrooms.mdd.usecase.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.usecase.dto.MessageDto;

@Mapper
public interface AutoMessageMapper {
    AutoMessageMapper MAPPER = Mappers.getMapper(AutoMessageMapper.class);

    MessageDto mapToMessageDto(MessageDto messageDto);
}
