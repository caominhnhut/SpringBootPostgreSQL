package com.sts.dto.book.request;

import java.util.List;

import com.sts.util.enums.BookStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Book code is required")
    private String bookCode;

    private BookStatus status;

    private String image;

    private List<BookTranslationRequest> translations;
}
