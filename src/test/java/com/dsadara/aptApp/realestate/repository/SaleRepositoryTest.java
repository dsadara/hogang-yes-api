package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.Sale;
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
class SaleRepositoryTest {
    @Autowired
    SaleRepository saleRepository;

    @Test
    @DisplayName("성공-findById()")
    void findById_Success() {
        //given
        Integer id = saleRepository.findAll().get(0).getId();

        //when
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("findById() 테스트 실패"));

        //then
        assertEquals(id, sale.getId());
    }
}