package com.sts.mapper;

import org.mapstruct.Mapper;

import com.sts.dto.book.request.BookRequest;
import com.sts.model.book.Book;

@Mapper(componentModel = "spring")
public interface BookResourceMapper{

    Book toBook(BookRequest bookRequest);
}
