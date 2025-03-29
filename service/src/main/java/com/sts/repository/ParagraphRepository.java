package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.entity.ParagraphEntity;

@Repository
public interface ParagraphRepository extends JpaRepository<ParagraphEntity, Long> {

}
