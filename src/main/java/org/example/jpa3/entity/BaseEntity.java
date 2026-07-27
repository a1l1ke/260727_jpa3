package org.example.jpa3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 감사 관련 속성을 주입 받을 수 있음
public abstract class BaseEntity {
    @CreatedDate // Instant, LocalDatetime
    @Column(updatable = false)
    private Instant createdAt; // 생성일시

    @LastModifiedDate
    private Instant updatedAt; // 수정일시
}
