# 호갱예스 API

호갱예스 API는 호갱노노 프로젝트의 API를 담당하는 모듈입니다.   
[batch-estate-engine](https://github.com/dsadara/batch-estate-engine) 프로젝트에서 생성한 **부동산 실거래 데이터를 API로 제공하는 기능을 담당하고 있습니다.
**

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