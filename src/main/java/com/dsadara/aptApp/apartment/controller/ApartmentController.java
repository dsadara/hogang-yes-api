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
            @RequestParam String searchKey,
            @RequestParam String searchValue,
            final Pageable pageable
    ) {
        switch (searchKey) {
            case "name":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByName(searchValue, pageable)
                                .map(ApartmentInfoSimple::fromDto));
            case "as1":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByAs1(searchValue, pageable)
                                .map(ApartmentInfoSimple::fromDto));
            case "as2":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByAs2(searchValue, pageable)
                                .map(ApartmentInfoSimple::fromDto));
            case "as3":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByAs3(searchValue, pageable)
                                .map(ApartmentInfoSimple::fromDto));
            case "as4":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByAs4(searchValue, pageable)
                                .map(ApartmentInfoSimple::fromDto));
            case "feature":
                return ResponseEntity.ok(
                        apartmentService.getApartmentByFeature(
                                        ApartmentFeature.valueOf(searchValue), pageable)
                                .map(ApartmentInfoSimple::fromDto));
            default:
                return null;
        }
    }

    //- 아파트 상세 정보 조회 API
    @GetMapping("/apt/detail")
    public ApartmentInfo getApartmentDetail(
            @RequestParam String aptCode
    ) {
        return apartmentService.getApartmentDetail(aptCode);
    }
}














