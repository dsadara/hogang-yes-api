package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.Rent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
class RentRepositoryTest {
    @Autowired
    RentRepository rentRepository;

    @Test
    @DisplayName("성공-findById()")
    void findById_Success() {
        //given
        Integer id = rentRepository.findAll().get(0).getId();

        //when
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("findById() 테스트 실패"));

        //then
        assertEquals(id, rent.getId());
    }
}