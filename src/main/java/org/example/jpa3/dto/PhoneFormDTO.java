package org.example.jpa3.dto;

import org.example.jpa3.entity.Phone;

public record PhoneFormDTO(String name) {
    public Phone toEntity() {
        return Phone.builder().name(name).build();
    }
}
