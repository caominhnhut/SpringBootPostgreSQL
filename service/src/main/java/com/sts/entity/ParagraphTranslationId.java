package com.sts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ParagraphTranslationId implements Serializable {
    @Column(name = "paragraph_id")
    private Long paragraphId;

    @Column(name = "locale")
    private String locale;
}