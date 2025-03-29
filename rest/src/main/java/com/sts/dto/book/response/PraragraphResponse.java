package com.sts.dto.book.response;

import java.util.List;

import com.sts.dto.book.MediaResourceDTO;
import com.sts.dto.book.ParagraphTranslationDTO;

import lombok.Data;

@Data
public class PraragraphResponse{

    private Long id;

    private int paragraphOrder;

    private String image;

    private List<ParagraphTranslationDTO> translations;

    private List<MediaResourceDTO> resources;
}
