package com.dsadara.aptApp.apartment.controller;

import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.ApartmentInfoSimple;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.service.ApartmentService;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import com.dsadara.aptApp.common.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.APARTMENT_TAG})
public class ApartmentController {
    private final ApartmentService apartmentService;

    @ApiOperation(
            value = "아파트 저장 API",
            notes = "아파트 상세 정보를 받아서 DB에 아파트를 저장합니다. 관리자만 사용 가능")
    @PostMapping("/apt")
    public CreateApartment.Response createApartment(
            @Parameter(description = "아파트 저장 모델", required = true)
            @RequestBody CreateApartment.Request request
    ) {
        return CreateApartment.Response.from(
                apartmentService.createApartment(request)
        );
    }

    @ApiOperation(
            value = "아파트 검색 API",
            notes = "search key와 search value를 사용해서 조건 검색을 수행하고 페이징 된 리스트를 반환합니다. \n\n" +
                    "search key 종류 \n" +
                    "1. name (단지명)\n" + "example: 아파트1, 아파트2, 아파트3 \n" +
                    "2. as1 (시도)\n" + "example: 땡땡시 \n" +
                    "3. as2 (시군구)\n" + "example: 땡땡구 \n" +
                    "4. as3 (읍면) \n" + "example: \n" +
                    "5. as4 (동리) \n" + "example: 땡땡동 \n" +
                    "6. feature (특징) \n" + "example: NEAR_STATION, GOOD_SCHOOL, COUPANG_ROCKET\n")
    @GetMapping("/apt")
    public ResponseEntity<Page<ApartmentInfoSimple>> getApartment(
            @Parameter(description = "검색 키", required = true, example = "name")
            @RequestParam String searchKey,
            @Parameter(description = "검색 값", required = true, example = "아파트1")
            @RequestParam String searchValue,
            @PageableDefault(sort="name", direction = Sort.Direction.ASC)
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
    @ApiOperation(
            value = "아파트 상세 정보 조회 API",
            notes = "아파트 코드를 받아서 아파트의 상세 정보를 조회합니다.\n" +
                    "아파트 검색 후 조회할 때 사용합니다.")
    @GetMapping("/apt/detail")
    public ApartmentInfo getApartmentDetail(
            @Parameter(description = "아파트 코드", required = true, example = "아파트코드1")
            @RequestParam String aptCode
    ) {
        return apartmentService.getApartmentDetail(aptCode);
    }
}














