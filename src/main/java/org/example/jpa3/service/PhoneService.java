package org.example.jpa3.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.entity.Phone;
import org.example.jpa3.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Phone findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Transactional // 더티 체킹 유도
    public void changeName(Long id, String name) {
//        Phone phone = phoneRepository.findById(id); // 스냅샷
        Phone phone = findById(id); // 스냅샷
        phone.changeName(name); // 차이점이 생기면 -> update문을 구동 (현 트랜잭션 하에서)
    }
}
