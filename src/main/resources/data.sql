-- 아파트 테이블 데이터
insert into apartment(created_at, updated_at, amount_recent, apprv_date, apt_code, as1, as2, as3, as4, bjd_code,
                      dong_no, drm_address, feature, house_no, name, parking_space_no, view_total, view_week)
values (now(), now(), null, '2000-12-12', '아파트코드1', '땡땡시', '땡땡구', null, '땡떙동', '법정동코드1', 20, '도로명주소',
        JSON_ARRAY('NEAR_STATION', 'GOOD_SCHOOL', 'NEAR_RIVER'), 500, '아파트1', 1000, 0, 0);

insert into apartment(created_at, updated_at, amount_recent, apprv_date, apt_code, as1, as2, as3, as4, bjd_code,
                      dong_no, drm_address, feature, house_no, name, parking_space_no, view_total, view_week)
values (now(), now(), null, '2001-12-12', '아파트코드2', '땡땡시', '땡땡구', null, '땡떙동', '법정동코드2', 40, '도로명주소',
        JSON_ARRAY('NEAR_STATION', 'GOOD_SCHOOL', 'NEAR_PARK', 'COUPANG_ROCKET'), 1000, '아파트2', 2000, 0, 0);

insert into apartment(created_at, updated_at, amount_recent, apprv_date, apt_code, as1, as2, as3, as4, bjd_code,
                      dong_no, drm_address, feature, house_no, name, parking_space_no, view_total, view_week)
values (now(), now(), null, '2002-12-12', '아파트코드3', '땡땡시', '땡땡구', null, '땡떙동', '법정동코드2', 30, '도로명주소',
        JSON_ARRAY('NEAR_STATION', 'NEAR_STREAM', 'NEAR_SUPERMARKET'), 700, '아파트3', 1400, 0, 0);