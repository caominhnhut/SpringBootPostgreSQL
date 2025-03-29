package com.sts.dto.book.request;

import java.util.List;

import com.sts.dto.book.MediaResourceDTO;
import com.sts.dto.book.ParagraphTranslationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParagraphRequest {

    @NotNull(message = "Paragraph order is required")
    private int paragraphOrder;

    private String image;

    private List<ParagraphTranslationDTO> translations;

    private List<MediaResourceDTO> resources;
}
