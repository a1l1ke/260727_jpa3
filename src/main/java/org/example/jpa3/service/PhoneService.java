package org.example.jpa3.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.entity.Phone;
import org.example.jpa3.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
        return;
        // 1. 여러 repository 등으로 테이블이 걸쳐 있을 때
        // 2. DB 외에도 외부 API 통신 등이 서비스에서 얽혀있을 때
//        save(Phone.builder().name("tx1").build());
//        System.out.println(1 / 0);
//        save(Phone.builder().name("tx2").build());
    }

    //    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.REQUIRES_NEW
    )
    public void tx2() {
        // 다른 서비스 등에서 이 메서드를 호출한다음
        // 그 서비스 메서드(트랜잭션) 중에 에러가 난다면
        // 같이 롤백
        System.out.println("PhoneService.tx2");
//        save(Phone.builder().name("REQUIRED").build());
        save(Phone.builder().name("REQUIRES_NEW").build());
    }

    // AOP -> 외부에서 호출을 해야 해당 처리가 명시적으로 진행
    // 스스로 호출 -> invoke -> self-invocation
    @Transactional
    public void tx2Out() {
        System.out.println("PhoneService.tx2Out");
        tx2(); // 내부에서 호출했기 때문에 무시.
        System.out.println(1 / 0);
    }

    @Transactional(
//            rollbackFor = Exception.class
            // RuntimeException <- 다 잡아줌...
            rollbackFor = {
                    NullPointerException.class,
                    NoSuchElementException.class
            },
            noRollbackFor = ArithmeticException.class
    )
    public void tx3() {
        // start transaction
        System.out.println("PhoneService.tx3");
        save(Phone.builder().name("tx3").build());
        System.out.println(1 / 0); // ArithmeticException
        // commit
    }
}
