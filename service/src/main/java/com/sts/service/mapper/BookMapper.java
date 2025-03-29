package com.sts.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sts.entity.BookEntity;
import com.sts.model.book.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "paragraphs", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "translations", ignore = true)
    BookEntity toBookEntity(Book book);
}
