package org.example.jpa3.repository;

import org.example.jpa3.entity.Phone;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findAll();

    void save(Phone phone);
}
