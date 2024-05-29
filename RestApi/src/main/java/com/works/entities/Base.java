package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @JsonIgnore
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @CreatedDate
    private Long createdDate;

    @JsonIgnore
    @LastModifiedBy
    private String lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    private Long lastModifiedDate;

}
