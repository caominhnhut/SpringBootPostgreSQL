package com.sts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class BookTranslationId implements Serializable {
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "locale")
    private String locale;
}