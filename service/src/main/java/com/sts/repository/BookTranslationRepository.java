package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.entity.BookTranslationEntity;
import com.sts.entity.BookTranslationId;

@Repository
public interface BookTranslationRepository extends JpaRepository<BookTranslationEntity, BookTranslationId>{
}