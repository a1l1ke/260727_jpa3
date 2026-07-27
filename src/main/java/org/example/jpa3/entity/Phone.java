package org.example.jpa3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    // LocalDate createdAt; -> service 시점에서 Instant.now() -> 서비스 단에서 주입.

    public void changeName(String name) {
        this.name = name;
    }

    // JPA -> 영속성 컨텍스트
    // @PrePersist // 생성 시 발동 (persist)
    // @PreUpdate // 수정 시 발동 (merge)
}
