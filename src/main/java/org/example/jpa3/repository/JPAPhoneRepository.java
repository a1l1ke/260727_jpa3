package org.example.jpa3.repository;

import org.example.jpa3.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPhoneRepository extends JpaRepository<Phone, Long> {
}
