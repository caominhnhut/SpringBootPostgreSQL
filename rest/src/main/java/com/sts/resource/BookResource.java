package com.sts.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.book.request.BookRequest;
import com.sts.model.ResponseData;
import com.sts.model.user.User;
import com.sts.model.user.UserUpdateRequest;
import com.sts.service.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookResource{

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> createBook(@RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.ok(null);
    }
}