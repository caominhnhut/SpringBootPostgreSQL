package com.sts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sts.dto.book.request.BookRequest;
import com.sts.dto.book.response.BookResponse;
import com.sts.model.book.Book;

@Mapper(componentModel = "spring")
public interface BookResourceMapper{

    Book toBook(BookRequest bookRequest);

    @Mapping(target = "createdAt", source = "book.createdAt")
    @Mapping(target = "updatedAt", source = "book.updatedAt")
    BookResponse toBookResponse(Book book);

}
