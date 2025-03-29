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
@Table(name = "paragraph_translations")
@Setter
@Getter
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