package com.sts.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resources")
public class MediaResourceEntity extends AuditMetaData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_to_resource", nullable = false)
    private String linkToResource;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "paragraph_id", nullable = false)
    private ParagraphEntity paragraph;
}