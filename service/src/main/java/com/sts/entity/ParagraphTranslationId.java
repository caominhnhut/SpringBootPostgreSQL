package com.sts.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ParagraphTranslationId implements Serializable {
    @Column(name = "paragraph_id")
    private Long paragraphId;

    @Column(name = "locale")
    private String locale;
}