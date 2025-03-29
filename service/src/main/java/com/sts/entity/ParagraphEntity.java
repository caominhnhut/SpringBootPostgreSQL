package com.sts.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "paragraphs")
public class ParagraphEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paragraph_order", nullable = false)
    private int paragraphOrder;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParagraphTranslationEntity> translations;

    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaResourceEntity> resources;
}