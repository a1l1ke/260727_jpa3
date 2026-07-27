package org.example.jpa3.repository;

import org.example.jpa3.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findAll();

    Page<Phone> findAll(Pageable pageable);

    void save(Phone phone);

    Phone findById(Long id);
}
