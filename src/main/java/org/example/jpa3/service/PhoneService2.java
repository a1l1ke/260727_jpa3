package org.example.jpa3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhoneService2 {
    private final PhoneService phoneService;

    @Transactional
    public void tx2Out() {
        System.out.println("PhoneService2.tx2Out");
        phoneService.tx2();
        System.out.println(1 / 0);
    }
}
