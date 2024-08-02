package com.dsadara.hogangYes.realestate.controller;

import com.dsadara.hogangYes.common.config.SwaggerConfig;
import com.dsadara.hogangYes.realestate.dto.RealEstateInfo;
import com.dsadara.hogangYes.realestate.dto.RealEstateInfoSimple;
import com.dsadara.hogangYes.realestate.dto.RentInfo;
import com.dsadara.hogangYes.realestate.dto.SaleInfo;
import com.dsadara.hogangYes.realestate.service.RealEstateService;
import com.dsadara.hogangYes.realestate.type.RealEstateType;
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
@Api(tags = {SwaggerConfig.REAL_ESTATE_TAG})
public class RealEstateController {

    private final RealEstateService realEstateService;

    @ApiOperation(
            value = "부동산 검색 API",
            notes = "search key와 search value를 사용해서 조건 검색을 수행하고 페이징 된 리스트를 반환합니다. \n\n" +
                    "search key 종류 \n" +
                    "1. name (부동산 이름)\n" + "example: 강변힐스테이트 \n" +
                    "2. beopJeongDongCode (법정동 코드)\n" + "example: 11500 \n" +
                    "3. beopJeongDong (법정동)\n" + "example: 염창동 \n" +
                    "4. parcelNumber (지번) \n" + "example: 299\n" +
                    "5. realEstateType (부동산 타입) \n" + "example: APT_RENT \n")
    @GetMapping("/realestate")
    public ResponseEntity<Page<RealEstateInfoSimple>> getRealEstate(
            @Parameter(description = "검색 키", required = true, example = "name")
            @RequestParam String searchKey,
            @Parameter(description = "검색 값", required = true, example = "강변힐스테이트")
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

    @ApiOperation(
            value = "부동산 상세 정보 조회 API",
            notes = "부동산의 식별자인 id를 받아서 부동산의 상세 정보를 조회합니다.\n" +
                    "부동산 검색 후 조회할 때 사용합니다.")
    @GetMapping("/realestate/detail")
    public RealEstateInfo getRealEstateDetail(
            @Parameter(description = "id", required = true, example = "1234432")
            @RequestParam String id
    ) {
        return realEstateService.getRealEstateDetail(id);
    }

    @ApiOperation(
            value = "부동산 전월세 상세 정보 조회 API",
            notes = "부동산의 식별자인 id를 통해서 전월세 거래의 상세 정보를 조회합니다."
    )
    @GetMapping("/realestate/rent")
    public RentInfo getRentDetail(
            @Parameter(description = "id", required = true, example = "1234432")
            @RequestParam String id
    ) {
        return realEstateService.getRentDetail(id);
    }

    @ApiOperation(
            value = "부동산 매매 상세 정보 조회 API",
            notes = "부동산의 식별자인 id를 통해서 매매 거래의 상세 정보를 조회합니다."
    )
    @GetMapping("/realestate/sale")
    public SaleInfo getSaleDetail(
            @Parameter(description = "id", required = true, example = "10649088")
            @RequestParam String id
    ) {
        return realEstateService.getSaleDetail(id);
    }

}