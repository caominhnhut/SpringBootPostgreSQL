package com.sts.service.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sts.entity.BookEntity;
import com.sts.entity.BookTranslationEntity;
import com.sts.entity.BookTranslationId;
import com.sts.entity.MediaResourceEntity;
import com.sts.entity.ParagraphEntity;
import com.sts.entity.ParagraphTranslationEntity;
import com.sts.entity.ParagraphTranslationId;
import com.sts.model.book.Book;
import com.sts.model.book.Paragraph;
import com.sts.repository.BookRepository;
import com.sts.repository.BookTranslationRepository;
import com.sts.repository.MediaResourceRepository;
import com.sts.repository.ParagraphRepository;
import com.sts.repository.ParagraphTranslationRepository;
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

    private final ParagraphRepository paragraphRepository;

    private final ParagraphTranslationRepository paragraphTranslationRepository;

    private final MediaResourceRepository mediaResourceRepository;

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

    public List<Book> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        return bookEntities.stream()
                .map(bookMapper::toBook)
                .toList();
    }

    public Book findById(Long bookId) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);

        return bookEntity.map(bookMapper::toBook).orElse(null);

    }

    public Long createParagraph(Long bookId, Paragraph paragraph) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        ParagraphEntity paragraphEntity = bookMapper.toParagraphEntity(paragraph);
        paragraphEntity.setBook(book);
        ParagraphEntity savedParagraph = paragraphRepository.save(paragraphEntity);

        if (paragraph.getTranslations() != null && !paragraph.getTranslations().isEmpty()) {
            var translationEntities = paragraph.getTranslations().stream()
                    .map(tra -> {
                        ParagraphTranslationEntity paraEntity = new ParagraphTranslationEntity();
                        paraEntity.setId(new ParagraphTranslationId(savedParagraph.getId(), tra.getLocale()));
                        paraEntity.setParagraphContent(tra.getParagraphContent());
                        paraEntity.setParagraph(savedParagraph);

                        return paraEntity;
                    })
                    .toList();

            paragraphTranslationRepository.saveAll(translationEntities);
        }

        if (paragraph.getResources() != null && !paragraph.getResources().isEmpty()) {
            var mediaResources = paragraph.getResources().stream()
                    .map(rs -> {
                        MediaResourceEntity mediaResource = new MediaResourceEntity();
                        mediaResource.setLinkToResource(rs.getLinkToResource());
                        mediaResource.setResourceType(rs.getResourceType());
                        mediaResource.setDescription(rs.getDescription());
                        mediaResource.setParagraph(savedParagraph);

                        return mediaResource;
                    })
                    .toList();

            mediaResourceRepository.saveAll(mediaResources);
        }

        return savedParagraph.getId();
    }
}
