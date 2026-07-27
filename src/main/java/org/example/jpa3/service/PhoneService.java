package org.example.jpa3.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.entity.Phone;
import org.example.jpa3.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public void save(Phone phone) {
        phoneRepository.save(phone);
    }
}
