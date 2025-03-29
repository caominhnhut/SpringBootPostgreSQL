package com.sts.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "paragraph_translations")
public class ParagraphTranslationEntity extends AuditMetaData implements Serializable{
    @EmbeddedId
    private ParagraphTranslationId id;

    @Column(name = "paragraph_content")
    private String paragraphContent;

    @ManyToOne
    @MapsId("paragraphId")
    @JoinColumn(name = "paragraph_id")
    private ParagraphEntity paragraph;
}