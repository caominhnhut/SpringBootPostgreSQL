package com.sts.dto.book;

import lombok.Data;

@Data
public class ParagraphTranslationDTO {

    private String locale;

    private String paragraphContent;
}