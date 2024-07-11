package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.dsadara.aptApp.realestate.type.RealEstateFeature.*;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class RealEstateRepositoryTest {
    @Autowired
    private RealEstateRepository realEstateRepository;

    @Autowired
    private Environment env;

    @BeforeEach
    void beforeEach() {
        realEstateRepository.deleteAll();

        realEstateRepository.save(RealEstate.builder()
                .aptCode("aptcode1")
                .name("apt1")
                .as1("**시").as2("**구").as3("**읍").as4("**동")
                .drmAddress("도로명주소1")
                .apprvDate(LocalDate.of(2001, 1, 1))
                .dongNo(10)
                .houseNo(1000)
                .parkingSpaceNo(2000)
                .bjdCode("bjdcode1")
                .feature(new ArrayList<>(Arrays.asList(NEAR_STATION, GOOD_SCHOOL)))
                .build());

        realEstateRepository.save(RealEstate.builder()
                .aptCode("aptcode2")
                .name("apt2")
                .as1("##시").as2("**구").as3("##읍").as4("**동")
                .drmAddress("도로명주소2")
                .apprvDate(LocalDate.of(2002, 2, 2))
                .dongNo(20)
                .houseNo(2000)
                .parkingSpaceNo(4000)
                .bjdCode("bjdcode2")
                .feature(new ArrayList<>(Arrays.asList(NEAR_STATION, NEAR_SUPERMARKET, COUPANG_ROCKET))).build());
    }

    @Test
    @DisplayName("성공-findByName()")
    void findByName_Success() {
        //given
        String aptName = "apt1";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByName(aptName, pageable);

        //then
        assertEquals(aptName, pageList.getContent().get(0).getName());
    }

    @Test
    @DisplayName("성공-findByAs1()")
    void findByAs1_Success() {
        //given
        String siDo = "**시";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByAs1(siDo, pageable);

        //then
        assertEquals(siDo, pageList.getContent().get(0).getAs1());
    }

    @Test
    @DisplayName("성공-findByAs2()")
    void findByAs2_Success() {
        //given
        String siGunGu = "**구";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByAs2(siGunGu, pageable);

        //then
        assertEquals(siGunGu, pageList.getContent().get(0).getAs2());
        assertEquals(siGunGu, pageList.getContent().get(1).getAs2());
    }

    @Test
    @DisplayName("성공-findByAs3()")
    void findByAs3_Success() {
        //given
        String eupMyeon = "**읍";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByAs3(eupMyeon, pageable);

        //then
        assertEquals(eupMyeon, pageList.getContent().get(0).getAs3());
    }

    @Test
    @DisplayName("성공-findByAs4()")
    void findByAs4_Success() {
        //given
        String dongLee = "**동";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByAs4(dongLee, pageable);

        //then
        assertEquals(dongLee, pageList.getContent().get(0).getAs4());
        assertEquals(dongLee, pageList.getContent().get(1).getAs4());
    }


    @Test
    @DisplayName("성공-findByFeature()")
    void findByFeature_Success() {
        // mysql 네이티브 쿼리를 사용한 테스트이므로 dev 프로파일(h2) 사용시 테스트하지 않기
        if (env.acceptsProfiles(Profiles.of("dev"))) {
            return;
        }

        //given
        RealEstateFeature realEstateFeature = GOOD_SCHOOL;

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByFeature(realEstateFeature, pageable);

        //then
        assertEquals(realEstateFeature, pageList.getContent().get(0).getFeature().get(1));
    }

    @Test
    @DisplayName("성공-findByAptCode()")
    void findByAptCode_Success() {
        //given
        String aptCode = "aptcode1";

        //when
        RealEstate realEstate = realEstateRepository.findByAptCode(aptCode)
                .orElseThrow(() -> new RuntimeException("findByAptCode() 테스트 실패"));

        //then
        assertEquals(aptCode, realEstate.getAptCode());
    }

    @Test
    @DisplayName("성공-existByName()")
    void existByName_Success() {
        //given
        String aptName = "apt1";

        //when
        Boolean isAptExist = realEstateRepository.existsByName(aptName);

        //then
        assertEquals(TRUE, isAptExist);
    }

    @Test
    @DisplayName("성공-existByAptCode()")
    void existByAptCode_Success() {
        //given
        String aptCode = "aptcode1";

        //when
        Boolean isAptExist = realEstateRepository.existsByAptCode(aptCode);

        //then
        assertEquals(TRUE, isAptExist);
    }

}
