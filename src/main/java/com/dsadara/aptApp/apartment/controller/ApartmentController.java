package com.dsadara.aptApp.apartment.controller;

import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.ApartmentInfoSimple;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.service.ApartmentService;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ApartmentInfoSimple> getApartment(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String as1,
            @RequestParam(required = false) String as2,
            @RequestParam(required = false) String as3,
            @RequestParam(required = false) String as4,
            @RequestParam(required = false) ApartmentFeature feature
    ) {
        if (name != null) {             // - 단지명 검색
            return apartmentService.getApartmentByName(name).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
        } else if (as1 != null) {       //- 시도별 검색
            return apartmentService.getApartmentByAs1(as1).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
        } else if (as2 != null) {       //- 시군구 검색
            return apartmentService.getApartmentByAs2(as2).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
        } else if (as3 != null) {       //- 읍면 검색
            return apartmentService.getApartmentByAs3(as3).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
        } else if (as4 != null) {       //- 동리 검색
            return apartmentService.getApartmentByAs4(as4).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
        } else if (feature != null) {   //- 특징 검색
            return apartmentService.getApartmentByFeature(feature).stream()
                    .map(ApartmentInfoSimple::fromEntity)
                    .collect(Collectors.toList());
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














