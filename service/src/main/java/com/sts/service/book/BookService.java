package com.sts.service.book;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sts.entity.BookEntity;
import com.sts.entity.BookTranslationEntity;
import com.sts.entity.BookTranslationId;
import com.sts.model.book.Book;
import com.sts.repository.BookRepository;
import com.sts.repository.BookTranslationRepository;
import com.sts.service.mapper.BookMapper;
import com.sts.util.enums.BookStatus;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class BookService {

    private final BookRepository bookRepository;

    private final BookTranslationRepository bookTranslationRepository;

    private final BookMapper bookMapper;

    @Transactional
    public Long createBook(Book book) {

        BookEntity bookEntity = bookMapper.toBookEntity(book);

        if (bookEntity.getStatus() == null ) {
            bookEntity.setStatus(BookStatus.ACTIVE);
        }

        BookEntity savedBookEntity = bookRepository.save(bookEntity);

        if (book.getTranslations() == null || book.getTranslations().isEmpty()) {
            return savedBookEntity.getId();
        }

        var translationEntities = book.getTranslations().stream()
                .map(tra -> {

                    BookTranslationEntity traEntity = new BookTranslationEntity();
                    traEntity.setId(new BookTranslationId(savedBookEntity.getId(), tra.getLocale()));
                    traEntity.setBook(savedBookEntity);
                    traEntity.setBookName(tra.getBookName());
                    traEntity.setBookDescription(tra.getBookDescription());

                    return traEntity;
                })
                .toList();

        bookTranslationRepository.saveAll(translationEntities);

        return savedBookEntity.getId();
    }
}
