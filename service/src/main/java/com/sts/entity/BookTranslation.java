package com.sts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book_translations")
public class BookTranslation extends AuditMetaData implements Serializable{
    @EmbeddedId
    private BookTranslationId id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_description")
    private String bookDescription;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
}