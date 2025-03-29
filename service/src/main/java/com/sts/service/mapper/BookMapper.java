package com.sts.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sts.entity.BookEntity;
import com.sts.entity.BookTranslationEntity;
import com.sts.entity.ParagraphEntity;
import com.sts.model.book.Book;
import com.sts.model.book.BookTranslation;
import com.sts.model.book.MediaResource;
import com.sts.model.book.Paragraph;
import com.sts.model.book.ParagraphTranslation;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "paragraphs", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "translations", ignore = true)
    BookEntity toBookEntity(Book book);

    @Mapping(target = "translations", source = "bookEntity.translations", qualifiedByName = "mapTranslations")
    @Mapping(target = "paragraphs", source = "bookEntity.paragraphs", qualifiedByName = "mapParagraphs")
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

    @Named("mapParagraphs")
    default List<Paragraph> mapParagraphs(List<ParagraphEntity> paragraphEntities) {
        return paragraphEntities.stream()
                .map(paragraph -> Paragraph.builder()
                        .id(paragraph.getId())
                        .paragraphOrder(paragraph.getParagraphOrder())
                        .image(paragraph.getImage())
                        .translations(paragraph.getTranslations().stream()
                                .map(translation -> ParagraphTranslation.builder()
                                        .locale(translation.getId().getLocale())
                                        .paragraphContent(translation.getParagraphContent())
                                        .build())
                                .toList())
                        .resources(paragraph.getResources().stream()
                                .map(resource -> MediaResource.builder()
                                        .resourceType(resource.getResourceType())
                                        .linkToResource(resource.getLinkToResource())
                                        .description(resource.getDescription())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "resources", ignore = true)
    ParagraphEntity toParagraphEntity(Paragraph paragraph);
}
