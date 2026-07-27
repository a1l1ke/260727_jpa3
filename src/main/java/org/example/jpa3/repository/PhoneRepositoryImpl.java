package org.example.jpa3.repository;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.entity.Phone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepository {
    private final JPAPhoneRepository phoneRepository;

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
    }
}
