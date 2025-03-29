package com.sts.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookTranslationId implements Serializable {
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "locale")
    private String locale;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTranslationId that = (BookTranslationId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(locale, that.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, locale);
    }
}