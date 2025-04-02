package com.sts.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.book.request.BookRequest;
import com.sts.dto.book.request.ParagraphRequest;
import com.sts.dto.book.response.BookResponse;
import com.sts.mapper.BookResourceMapper;
import com.sts.model.ResponseData;
import com.sts.service.book.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/no-auth/books")
@RequiredArgsConstructor
@Slf4j
public class BookResource{

    private final BookService bookService;

    private final BookResourceMapper bookResourceMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> createBook(@RequestBody @Valid BookRequest bookRequest) {

        var book = bookResourceMapper.toBook(bookRequest);

        Long bookId = bookService.createBook(book);

        ResponseData<Long> responseData = new ResponseData<>();
        responseData.setData(bookId);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<BookResponse>>> getAllBooks() {
        var books = bookService.getAllBooks();
        var bookResponses = books.stream()
                .map(bookResourceMapper::toBookResponse)
                .toList();

        ResponseData<List<BookResponse>> responseData = new ResponseData<>();
        responseData.setData(bookResponses);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<ResponseData<BookResponse>> findBookById(@PathVariable("book-id") Long bookId) {
        var book = bookService.findById(bookId);

        var bookResponses = bookResourceMapper.toBookResponse(book);

        ResponseData<BookResponse> responseData = new ResponseData<>();
        responseData.setData(bookResponses);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{book-id}/paragraph")
    public ResponseEntity<ResponseData<Long>> createParagraph(@PathVariable("book-id") Long bookId, @RequestBody @Valid ParagraphRequest paragraphRequest) {

        var paragraph = bookResourceMapper.toParagraph(paragraphRequest);
        Long paragraphId = bookService.createParagraph(bookId, paragraph);

        ResponseData<Long> responseData = new ResponseData<>();
        responseData.setData(paragraphId);

        return ResponseEntity.ok(responseData);
    }
}