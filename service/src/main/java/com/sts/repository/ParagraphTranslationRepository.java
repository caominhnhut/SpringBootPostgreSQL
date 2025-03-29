package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.entity.ParagraphTranslationEntity;
import com.sts.entity.ParagraphTranslationId;

@Repository
public interface ParagraphTranslationRepository extends JpaRepository<ParagraphTranslationEntity, ParagraphTranslationId>{
}