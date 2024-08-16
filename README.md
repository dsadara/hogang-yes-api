# 호갱예스 API

부동산 데이터를 조회하는 API 입니다. Spring MVC로 사용자의 HTTP 요청을 처리합니다.

추후 코드의 변경이 많을 수 있다 판단이 들어서 CI/CD를 도입했습니다.   
Travis CI로 **자동 빌드**를 구현했고, Amazon S3, Amazon CodeDeploy로 **자동 배포**를 구현했습니다.

또한 Ngnix의 리버스 프록시 기능을 사용하여 **무중단 배포**를 구축했고 서비스의 다운타임을 최소화 했습니다.

### Backend

- **JDK**: 1.8
- **Spring Boot**: 2.7.7
  - 주요 기술 스택: Spring Data JPA, Junit5, Spring MVC, Tomcat

### Infra

- **Public Cloud**
    - **Amazon EC2**: t2.micro (Amazon Linux 2023)
    - **Amazon RDS**: db.t3.micro (MariaDB 10.11)
- **Local Environment**
    - **Apple M2 Pro** (macOS Ventura 13.4.1)
    - **H2 Database**: 2.1.214

### CI/CD

- **CI**: Github, Travis CI, Amazon S3
- **CD**: Amazon CodeDeploy

### Tools

- **Documentation**: Springfox Swagger 3.0.0 *[(Swagger 명세서 링크)](http://3.37.47.2:80/swagger-ui/index.html)*
- **Development**: Intellij IDEA Ultimate