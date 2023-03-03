# 호갱예스(Hogangyes)

아파트 정보, 아파트 거래를 할 수 있는 서비스 백엔드 입니다.   
['아파트 정보 데이터'](https://www.data.go.kr/data/15073271/fileData.do)
와 ['아파트 실거래 정보 데이터'](https://www.data.go.kr/data/3050988/fileData.do)를 사용하였고,  
아파트 실거래가 서비스인 '호갱노노'를 참고해서 만들었습니다.

## 1. API 명세서

Swagger UI로 현재까지 구현한 기능들을 테스트해볼 수 있습니다.

[Swagger UI 주소](http://ec2-54-180-47-56.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html)

## 2. 무엇을 중점으로 프로젝트를 진행했나요?

- **협업한다 생각하고 Github를 사용했습니다.**
    - Issue, Branch, Pull Request 등 Github의 다양한 기능들을 사용해봤습니다.
    - Commit 메시지, Issue와 PR 내용을 신경 써서 작성했습니다.


- **모르는 기술들을 새로 배워 적용해보았습니다.**
    - 인프라: Github Action, AWS(EC2, RDS, S3, Code Deploy)
    - (사용한 기술들을 추가할 예정입니다!)


- **테스트 코드를 꼼꼼히 작성했습니다.**
    - TDD는 아니지만 차후 TDD를 적용해보기 위해 테스트코드 작성에 익숙해지려고 합니다.
    - Mockito를 이용해서 단위 테스트 코드를 작성했습니다.

## 3. 기술스택

### 사용

- Java 8
- Spring Boot 2.7.7
- Mysql
- Jpa
- Junit5
- Mockito
- Swagger
- Aws
    - ec2, rds, s3, code deploy

### 사용예정

- Redis
- Spring Security
- Spring WebSocket
- Elastic Search

## 4. 기능

### 공통 기능

- 아파트
    - 아파트 검색
        - 단지명, 시도, 시군구, 읍면, 동리, 특징별 검색
    - 아파트 정보 조회
        - 아파트 정보, 아파트 최근 거래내역, 해당 매물, 댓글, 인근 중개사, 주간 조회수
- 매물
    - 매물 조회
- 중개사
    - 프로필 조회
    - 담당 매물 조회

### 일반회원 기능

- 아파트
    - 관심 아파트 추가
    - 아파트 댓글 작성
- 매물
    - 매물 내놓기
    - 매물 거래신청
- 중개사
    - 중개사 평점 후기 작성
    - 중개사와 채팅 상담
- 회원
    - 회원가입, 로그인, 이메일 인증
    - 내 메뉴
        - 내 관심 아파트, 내 댓글, 내 매물, 내 중개사 평점 후기, 내 상담 기록 조회

### 중개사 기능

- 중개사
    - 회원가입, 로그인, 이메일 인증
    - 내 메뉴
        - 프로필 수정
        - 상담 기록 조회
- 매물
    - 매물 거래신청 관리
        - 거래신청조회, 거래신청승인, 거래신청반려
- 회원
    - 채팅 상담
    - 고객 매물 관리
        - 거래완료여부, 거래진행여부 업데이트

## 5. 데이터베이스 구조

![image](https://user-images.githubusercontent.com/22488593/222758253-8d4a6970-5b98-47db-8bff-d4bdd2f8d716.png)   
[erd 상세보기](https://www.erdcloud.com/d/mhdvDKhPQgq9Kvvbf)

## 6. 개발일지

https://youthful-level-f1e.notion.site/efc0460136b64f3e96477c050d211e82?v=c31c51ec0db04e10b897f29238d7fe69
