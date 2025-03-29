package com.sts.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "book_translations")
@Getter
@Setter
public class BookTranslationEntity extends AuditMetaData implements Serializable{
    @EmbeddedId
    private BookTranslationId id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_description")
    private String bookDescription;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookEntity book;
}