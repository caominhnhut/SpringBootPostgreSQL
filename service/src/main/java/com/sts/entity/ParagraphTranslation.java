package com.sts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "paragraph_translations")
public class ParagraphTranslation extends AuditMetaData implements Serializable{
    @EmbeddedId
    private ParagraphTranslationId id;

    @Column(name = "paragraph_content")
    private String paragraphContent;

    @ManyToOne
    @MapsId("paragraphId")
    @JoinColumn(name = "paragraph_id")
    private Paragraph paragraph;
}