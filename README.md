## 연구소 자바개발자 내부 교육(Spring Boot)

### 커리큘럼
* 상황에 따라 변경될 수 있습니다.
* 교욕 방향
  * Spring Framework의 기본은 알고 있다고 가정하고 개발자들이 의외로 잘 모르는 개념 위주로 진행
  * 실전에 가까운 예제로 진행
* 매주 교육 당일에 교육 자료와 소스 코드를 branch에 공유 합니다.
    * branch 명 : week01 ~ week10
* 만들어볼 프로젝트
    * https://htmlpreview.github.io/?https://github.com/hojinDev/restdocs-sample/blob/master/html/step3.html

### **1주차**

- **Spring Boot 동작원리**
    - Spring Boot 프로젝트 구조
    - 의존성 관리 이해
    - 자동 설정 이해
    - Spring Boot properties
- **개발환경 구성(DevOps 를 지향)**
    - Docker로 Nginx, MariaDB 설치
    - Flyway로 DB 형상관리
- **Spring Boot DevTools**

### **2주차**

- **외부 설정, 프로파일**
- **REST API  설계 1**
    - Rest API 스펙
    - SpringMVC 1
        - @RequestBody, @ModelAttribute, @RequestParam, @PathVariable, @RequesPart

### **3주차**

- **Domain 객체 설계 전략**
- **Lombok 실무에서 사용**
- Mordern JAVA
    - java.time(JSR-310)
    - Optional
    - New File API(Java 7)
    - 기본 문법은 설명하지 않고 코드위주로 진행
        - 메신저, 그룹웨어 Global Time 및 프레임웍 난독화를 적용하면서 보완 되거나 설명이 필요한 코드 위주로 진행


### 4주차

- **REST API 설계 2(응답, 예외처리 전략)**
    - Spring MVC 2
        - ExceptionHanding(@RestControllerAdvice, @ExceptionHandler, ResponseEntry)
        - FileUpload
    - 예외를 공통화
- **Spring Data JPA(QueryDSL)**
    - JPA는 도입하지 않기로 하여 기술 교육 코드를 이해가능한 수준까지만 진행

### 5주차

- **IoC, DI 개념**
    - 생성자 주입을 사용해야 하는 이유
- @Transaction 사용시 주의 사항 및 AOP
- **Bean Validation 2.0 (JSR-380)**
    - Custom Validation Annotation 만들기
        - 비밀번호 체크
        - 전화번호 체크
    - Custom Meaaage
- **Spring Boot Actuator**

### **6주차**

- **REST API 설계 3(API 문서화)**
    - 단위 테스트가 필요한 이유?
        - Junit 5, AssertJ등
    - **Spring Rest Doc**
        - Swagger vs Spring Rest Doc
            - Spring Rest Doc 선택한( 실전에서 선택되는) 이유
        - Enum을 활용한 코드문서화
    - Markdown(READ.md)  사용예

### **7~9주차**

- 아직 구체적 내용은 확정 안됨
- **Spring Batch**
- **Spring Securiry**
    - 기본 개념은 알고 있다고 가정하고 OAuth2, JWT 를 활용한 인증서버 개발 진행을 생각 하고 있음
    - 1주 더 전행될수 있음


