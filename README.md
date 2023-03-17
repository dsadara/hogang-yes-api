# 호갱예스(Hogangyes)

국토교통부 아파트 실거래가를 조회 할 수 있는 서비스 입니다.   
 ['호갱노노'](https://hogangnono.com/) 앱을 참고해서 만들었습니다.

## 1. API 명세서

Swagger UI로 구현한 기능들을 테스트해볼 수 있습니다.

[Swagger UI 주소](http://ec2-54-180-47-56.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html)

## 2. 무엇을 중점으로 프로젝트를 진행했나요?

- **협업한다 생각하고 Github를 사용했습니다.**
    - Issue, Branch, Pull Request 등 Github의 다양한 기능들을 사용해봤습니다.
    - Commit 메시지, Issue와 PR 내용을 신경 써서 작성했습니다.


- **새로운 기술들을 배워 적용했습니다.**
    - 인프라: Github Action, AWS(EC2, RDS, S3, Code Deploy)
    - (사용한 기술들을 추가할 예정입니다!)


- **TDD를 지향합니다.**
    - 테스트 코드의 중요성을 인지하고 테스트 코드에 공을 들였습니다. 
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
- Elastic Search

## 4. 기능

### 공통 기능

- 아파트
    - 아파트 검색
        - 단지명, 시도, 시군구, 읍면, 동리, 특징별 검색
    - 아파트 조회
        - 아파트 상세정보, 최근 거래내역
- 회원
  - 회원가입
    - 이메일 인증
  - 로그인
  - 회원정보수정
  - 회원탈퇴

### 회원 기능

- 아파트
    - 아파트 등록
    - 아파트 수정
    - 아파트 삭제
  
## 5. 기타
* [erd 상세보기](https://www.erdcloud.com/d/yXtqPze4zD5R8TQDj)
* [개발일지](https://youthful-level-f1e.notion.site/efc0460136b64f3e96477c050d211e82?v=c31c51ec0db04e10b897f29238d7fe69)
* 사용 데이터1: [국토교통부_공동주택 단지 기본 정보](https://www.data.go.kr/data/15073271/fileData.do)
* 사용 데이터2: [국토교통부_실거래가 정보](https://www.data.go.kr/data/3050988/fileData.do)