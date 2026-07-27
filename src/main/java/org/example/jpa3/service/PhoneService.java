package org.example.jpa3.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.entity.Phone;
import org.example.jpa3.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Phone> findAll(Pageable pageable) {
        return phoneRepository.findAll(pageable);
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

    @Transactional
//    @Transactional(readOnly = true)
    // readOnly = true -> 최적화를 위한 힌트
    // 강제로 insert를 차단하지는 않기 때문에
    // throw 발생으로 인해 로직이 차단되며 rollback이 일어나지면
    // -> db 엔진의 종류나 실행 순서 등에 의해서 의도한 작업 X.
    public void tx1() {
        // 1. 여러 repository 등으로 테이블이 걸쳐 있을 때
        // 2. DB 외에도 외부 API 통신 등이 서비스에서 얽혀있을 때
        save(Phone.builder().name("tx1").build());
        System.out.println(1 / 0);
        save(Phone.builder().name("tx2").build());
    }
}
