package com.sts.dto.book;

import lombok.Data;

@Data
public class BookTranslationDTO{

    private String locale;

    private String bookName;

    private String bookDescription;
}
