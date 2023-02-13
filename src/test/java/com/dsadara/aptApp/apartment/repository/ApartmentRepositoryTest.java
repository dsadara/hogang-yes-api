package com.dsadara.aptApp.apartment.repository;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dsadara.aptApp.apartment.type.ApartmentFeature.*;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class ApartmentRepositoryTest {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    Environment env;

    @BeforeEach
    void beforeEach() {
        apartmentRepository.deleteAll();

        apartmentRepository.save(Apartment.builder()
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

        apartmentRepository.save(Apartment.builder()
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
    void findByNameSuccess() {
        //given
        String aptName = "apt1";

        //when
        List<Apartment> apartmentList = apartmentRepository.findByName(aptName);

        //then
        assertEquals(aptName, apartmentList.get(0).getName());
    }

    @Test
    @DisplayName("성공-findByAs1()")
    void findByAs1Success() {
        //given
        String siDo = "**시";

        //when
        List<Apartment> apartments = apartmentRepository.findByAs1(siDo);

        //then
        assertEquals(siDo, apartments.get(0).getAs1());
    }

    @Test
    @DisplayName("성공-findByAs2()")
    void findByAs2Success() {
        //given
        String siGunGu = "**구";

        //when
        List<Apartment> apartments = apartmentRepository.findByAs2(siGunGu);

        //then
        assertEquals(siGunGu, apartments.get(0).getAs2());
        assertEquals(siGunGu, apartments.get(1).getAs2());
    }

    @Test
    @DisplayName("성공-findByAs3()")
    void findByAs3Success() {
        //given
        String eupMyeon = "**읍";

        //when
        List<Apartment> apartments = apartmentRepository.findByAs3(eupMyeon);

        //then
        assertEquals(eupMyeon, apartments.get(0).getAs3());
    }

    @Test
    @DisplayName("성공-findByAs4()")
    void findByAs4Success() {
        //given
        String dongLee = "**동";

        //when
        List<Apartment> apartments = apartmentRepository.findByAs4(dongLee);

        //then
        assertEquals(dongLee, apartments.get(0).getAs4());
        assertEquals(dongLee, apartments.get(1).getAs4());
    }


    @Test
    @DisplayName("성공-findByFeature")
    void findByFeatureSuccess() {
        // mysql 네이티브 쿼리를 사용한 테스트이므로 mysql 사용시에만 테스트하기 (local 프로파일)
        if (!env.acceptsProfiles(Profiles.of("local"))) {
            return;
        }

        //given
        ApartmentFeature apartmentFeature = GOOD_SCHOOL;

        //when
        List<Apartment> apartmentList = apartmentRepository.findByFeature(apartmentFeature);

        //then
        assertEquals(apartmentFeature, apartmentList.get(0).getFeature().get(1));
    }

    @Test
    @DisplayName("성공-findByAptCode")
    void findByAptCodeSuccess() {
        //given
        String aptCode = "aptcode1";

        //when
        Apartment apartment = apartmentRepository.findByAptCode(aptCode)
                .orElseThrow(() -> new RuntimeException("findByAptCode() 테스트 실패"));

        //then
        assertEquals(aptCode, apartment.getAptCode());
    }

    @Test
    @DisplayName("성공-existByName")
    void existByNameSuccess() {
        //given
        String aptName = "apt1";

        //when
        Boolean isAptExist = apartmentRepository.existsByName(aptName);

        //then
        assertEquals(TRUE, isAptExist);
    }

    @Test
    @DisplayName("성공-existByAptCode")
    void existByAptCodeSuccess() {
        //given
        String aptCode = "aptcode1";

        //when
        Boolean isAptExist = apartmentRepository.existsByAptCode(aptCode);

        //then
        assertEquals(TRUE, isAptExist);
    }


}
