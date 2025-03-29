package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.entity.MediaResourceEntity;

@Repository
public interface MediaResourceRepository extends JpaRepository<MediaResourceEntity, Long> {


}
