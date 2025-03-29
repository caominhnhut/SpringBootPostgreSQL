package com.sts.dto.book.request;

import lombok.Data;

@Data
public class BookTranslationRequest{

    private String locale;

    private String bookName;

    private String bookDescription;
}
