package com.example.board.crud;

public interface Converter<DTO, ENTITY> {

    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);

}
