-- 부동산 테이블 데이터
insert into real_estate (beop_jeong_dong, beop_jeong_dong_code, construct_year, contract_day, contract_month,
                         contract_year, floor, jeon_yong_area, name, parcel_number, real_estate_type)
values ('염창동', '11500', 2005, 22, 1, 2011, 12, 74, '강변힐스테이트아파트', '299', 'APT_TRADE');

insert into real_estate (beop_jeong_dong, beop_jeong_dong_code, construct_year, contract_day, contract_month,
                         contract_year, floor, jeon_yong_area, name, parcel_number, real_estate_type)
values ('염창동', '11500', 2000, 30, 12, 2001, 10, 102, '염창현대1차아파트', '288', 'APT_RENT');

insert into real_estate (beop_jeong_dong, beop_jeong_dong_code, construct_year, contract_day, contract_month,
                         contract_year, floor, jeon_yong_area, name, parcel_number, real_estate_type)
values ('염창동', '11500', 2010, 14, 5, 2014, 4, 102, '염창동롯데캐슬아파트', '272-11', 'APT_TRADE');

-- sale -> id  	cancel_deal_day  	cancel_deal_type  	agent_address  	deal_amount  	deal_type
insert into sale (id, cancel_deal_day, cancel_deal_type, agent_address, deal_amount, deal_type)
values (1, '10.02.01', 'O', '서울 강서구', 110000, '중개거래');

insert into sale (id, cancel_deal_day, cancel_deal_type, agent_address, deal_amount, deal_type)
values (3, '15.12.01', 'O', '서울 강서구', 100000, '중개거래');

-- rent-> id  	contract_period  	contract_type  	deposit  	deposit_before  	monthly_rent  	monthly_rent_before  	request_renewal_right  	si_gun_gu
insert into rent ( id, contract_period, contract_type, deposit, deposit_before, monthly_rent
                 , monthly_rent_before, request_renewal_right, si_gun_gu)
values (2, '23.08~25.08', '갱신', 10000, 9000, 200, 185, '사용', null);