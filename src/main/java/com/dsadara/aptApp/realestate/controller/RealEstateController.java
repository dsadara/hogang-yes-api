package com.dsadara.aptApp.realestate.controller;

import com.dsadara.aptApp.common.config.SwaggerConfig;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.dto.RealEstateInfoSimple;
import com.dsadara.aptApp.realestate.service.RealEstateService;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.APARTMENT_TAG})
public class RealEstateController {

    private final RealEstateService realEstateService;

    //    @ApiOperation(
//            value = "아파트 검색 API",
//            notes = "search key와 search value를 사용해서 조건 검색을 수행하고 페이징 된 리스트를 반환합니다. \n\n" +
//                    "search key 종류 \n" +
//                    "1. name (단지명)\n" + "example: 아파트1, 아파트2, 아파트3 \n" +
//                    "2. as1 (시도)\n" + "example: 땡땡시 \n" +
//                    "3. as2 (시군구)\n" + "example: 땡땡구 \n" +
//                    "4. as3 (읍면) \n" + "example: \n" +
//                    "5. as4 (동리) \n" + "example: 땡땡동 \n" +
//                    "6. feature (특징) \n" + "example: NEAR_STATION, GOOD_SCHOOL, COUPANG_ROCKET\n")
    @GetMapping("/realestate")
    public ResponseEntity<Page<RealEstateInfoSimple>> getRealEstate(
            @Parameter(description = "검색 키", required = true, example = "name")
            @RequestParam String searchKey,
            @Parameter(description = "검색 값", required = true, example = "아파트1")
            @RequestParam String searchValue,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) final Pageable pageable
    ) {
        switch (searchKey) {
            case "name":
                return ResponseEntity.ok(
                        realEstateService.getRealEstateByName(searchValue, pageable)
                                .map(RealEstateInfoSimple::fromDto));
            case "beopJeongDongCode":
                return ResponseEntity.ok(
                        realEstateService.getRealEstateByBeopJeongDongCode(searchValue, pageable)
                                .map(RealEstateInfoSimple::fromDto));
            case "beopJeongDong":
                return ResponseEntity.ok(
                        realEstateService.getRealEstateByBeopJeongDong(searchValue, pageable)
                                .map(RealEstateInfoSimple::fromDto));
            case "parcelNumber":
                return ResponseEntity.ok(
                        realEstateService.getRealEstateByParcelNumber(searchValue, pageable)
                                .map(RealEstateInfoSimple::fromDto));
            case "realEstateType":
                return ResponseEntity.ok(
                        realEstateService.getRealEstateByRealEstateType(
                                        RealEstateType.valueOf(searchValue), pageable)
                                .map(RealEstateInfoSimple::fromDto));
            default:
                return null;
        }
    }

    //- 아파트 상세 정보 조회 API
    @ApiOperation(
            value = "아파트 상세 정보 조회 API",
            notes = "아파트 코드를 받아서 아파트의 상세 정보를 조회합니다.\n" +
                    "아파트 검색 후 조회할 때 사용합니다.")
    @GetMapping("/realestate/detail")
    public RealEstateInfo getRealEstateDetail(
            @Parameter(description = "id", required = true, example = "1")
            @RequestParam String id
    ) {
        return realEstateService.getRealEstateDetail(id);
    }

}














