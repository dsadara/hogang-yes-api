package com.dsadara.aptApp.apartment.controller;

import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.ApartmentInfoSimple;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.service.ApartmentService;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    // 아파트 저장 API (관리자)
    @PostMapping("/apt")
    public CreateApartment.Response createApartment(
            @RequestBody CreateApartment.Request request
    ) {
        return CreateApartment.Response.from(
                apartmentService.createApartment(request)
        );
    }

    // 아파트 검색 API
    @GetMapping("/apt")
    public ResponseEntity<Page<ApartmentInfoSimple>> getApartment(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String as1,
            @RequestParam(required = false) String as2,
            @RequestParam(required = false) String as3,
            @RequestParam(required = false) String as4,
            @RequestParam(required = false) ApartmentFeature feature,
            final Pageable pageable
    ) {
        if (name != null) {             // - 단지명 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByName(name, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        } else if (as1 != null) {       //- 시도별 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByAs1(as1, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        } else if (as2 != null) {       //- 시군구 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByAs2(as2, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        } else if (as3 != null) {       //- 읍면 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByAs3(as3, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        } else if (as4 != null) {       //- 동리 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByAs4(as4, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        } else if (feature != null) {   //- 특징 검색
            return ResponseEntity.ok(
                    apartmentService.getApartmentByFeature(feature, pageable)
                            .map(ApartmentInfoSimple::fromDto));
        }
        return null;
    }

    //- 아파트 상세 정보 조회 API
    @GetMapping("/apt/detail")
    public ApartmentInfo getApartmentDetail(
            @RequestParam String aptCode
    ) {
        return apartmentService.getApartmentDetail(aptCode);
    }
}














