package com.sts.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.book.request.BookRequest;
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

    private final BookService bokService;

    private final BookResourceMapper bookResourceMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> createBook(@RequestBody @Valid BookRequest bookRequest) {

        var book = bookResourceMapper.toBook(bookRequest);

        Long bookId = bokService.createBook(book);

        ResponseData<Long> responseData = new ResponseData<>();
        responseData.setData(bookId);

        return ResponseEntity.ok(responseData);
    }
}