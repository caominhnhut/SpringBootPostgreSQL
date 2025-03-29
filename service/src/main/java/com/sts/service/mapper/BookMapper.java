package com.sts.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sts.entity.BookEntity;
import com.sts.entity.BookTranslationEntity;
import com.sts.model.book.Book;
import com.sts.model.book.BookTranslation;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "paragraphs", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "translations", ignore = true)
    BookEntity toBookEntity(Book book);

    @Mapping(target = "translations", source = "bookEntity.translations", qualifiedByName = "mapTranslations")
    Book toBook(BookEntity bookEntity);

    @Named("mapTranslations")
    default List<BookTranslation> mapTranslations(List<BookTranslationEntity> translationEntities) {
        return translationEntities.stream()
                .map(entity -> BookTranslation.builder()
                        .locale(entity.getId().getLocale())
                        .bookName(entity.getBookName())
                        .bookDescription(entity.getBookDescription())
                        .build())
                .toList();
    }
}
