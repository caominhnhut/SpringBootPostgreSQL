package com.sts.dto.book.response;

import java.time.Instant;
import java.util.List;

import com.sts.dto.book.BookTranslationDTO;
import com.sts.util.enums.BookStatus;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;

    private BookStatus status;

    private String image;

    private Instant createdAt;

    private Instant updatedAt;

    private List<BookTranslationDTO> translations;

    private List<PraragraphResponse> paragraphs;
}