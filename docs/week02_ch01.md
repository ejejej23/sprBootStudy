# Spring Boot 프로퍼티, 외부 설정

## properties 파일
```properties
server.port: 80
```
##  yml(YAML) 파일
```yaml
server:
    port: 80
```
> YAML(YAML Ain't Markup Language): 'YAML은 마크업 언어가 아니다'라는 뜻으로 문서 마크업이 아닌 데이터 중심이라는 것을 의미함. 
> 가독성이 좋고 이해하기 수월한 문법으로 작성되어 있음.

* Spring Boot의 설정 파일로 기존에는 .properties 파일을 많이 사용했지만 표현의 한계로 인하여 최근에는 YAML 파일을 더 많이 사용
* YAML 설정을 하려면 SnakeYAML 라이브러리를 포함해야 하지만 spring-boot-starter에 SnakeYAML 라이브러리가 내장되어 있어서 별도의 설정없이 .yml 파일의 사용이 가능
* application.properties와 application.yml 파일이 모두 생성되어 있다면 application.yml 파일만 오버라이드되어 적용

### 설정 우선순위
| 위치 | 우선순위        |
|---|-------------|
커맨드 라인 아규먼트 | 높음
java:comp/env JNDI 애트리뷰트 |
OS 환경 변수 |
file:/config (JAR 밖에 있는) |
file:/ (JAR 밖에 있는)|
classpath:/config (JAR 안에 있는)|
classpath:/ (JAR 안에 있는)| 낮음

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config

## AML 파일 매핑하기
* YAML 파일을 사용하면 depth에 따라 관계 구분이 이루어지기 때문에 List, Set, Map 등 바인딩형의 다양한 매핑을 간편하게 할 수 있음
* @Value와 @ConfigurationProperties 어노테이션으로 매핑함

## @ConfigurationProperties vs @Value
### @ConfigurationProperties
* 여러 프로퍼티를 묶어서 읽어올 수 있음
* 빈으로 등록해서 다른 빈에 주입할 수 있음
  * @EnableConfigurationProperties
  * @Component
  * @Bean
* 융통성 있는 바인딩
  * context-path (케밥)
  * context_path (언드스코어)
  * contextPath (캐멀)

* 프로퍼티 타입 컨버전
  * @DurationUnit
* 프로퍼티 값 검증
  * @Validated
  * JSR-303 (@NotNull, ...)
* 메타 정보 생성

### @Value
  * SpEL 을 사용할 수 있
  * 지만...
  * 위에 있는 기능들은 전부 사용 못합

## @ConfigurationProperties 사용하기
* @ConfigurationProperties 어노테이션을 사용하면 다양한 타입의 프로퍼티를 매핑할 수 있음 
* 기본적으로 prefix를 사용하여 바인딩  
* application.yml에 아래과 같이 프로퍼티를 추가

```yaml
app-config:
  jwt:
    base64Secret: YWxsZ295b3VAKUApMTE5MTQzMSkpOCkpeWFubmlzaGluaXNzdXBlcmdlbml1c3Rsc3RqcnJic2Rod2pkYW5ydGxzZ3lkbmpz
    tokenExpirationTime: 8
    refreshTokenExpirationTime: 48

  timezone:
    defaultTimezone: 'UTC'
    convertTimezone: 'Asia/Seoul'
```

* TimezoneProperties

```java
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="app-config.timezone")
public class TimezoneProperties {
  private String defaultTimezone;
  private String convertTimezone;
}
```
* JwtProperties

```java
@Getter
@Setter
@Valid
@Configuration
@ConfigurationProperties(prefix="app-config.jwt")
public class JwtProperties {
  private String base64Secret;
  /**
   * JWT Token 만료 시간
   */
  @DurationUnit(ChronoUnit.HOURS)
  @Min(1)
  @Max(48)
  private Duration tokenExpirationTime = Duration.ofHours(8);
  @DurationUnit(ChronoUnit.HOURS)
  private Duration refreshTokenExpirationTime = Duration.ofHours(8);
}
```

* PropertyTest.java

```java
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PropertyTest {
  @Autowired
  TimezoneProperties timezoneProperties;

  @Autowired
  JwtProperties jwtProperties;

  @Test
  public void propertyMapping01() throws Exception {
    assertThat(timezoneProperties.getDefaultTimezone()).isEqualTo("UTC");
  }

  @Test
  @DisplayName("시간 단위로 매핑")
  public void propertyMapping02() throws Exception {
    assertThat(jwtProperties.getTokenExpirationTime()).isEqualTo(Duration.ofHours(8));
    assertThat(jwtProperties.getRefreshTokenExpirationTime()).isEqualTo(Duration.ofHours(48));
  }
}
```

### 프로퍼티 문서화
https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html#configuration-metadata.annotation-processor

#### spring-boot-configuration-processor 의존성 
* configuration processor 는 @ConfigurationProperties 를 가지고 있는 모든 클래스와 메소드를 스캔
  * 이를 통해서 configuration parameter에 접근하고, configuration metadata 를 자동 생성합
* Maven
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
* Gradle

```groovy
dependencies {
    annotationProcessor "org.springframework.boot:spring-boot-configuration-processor"
}
```

### Spring Profiles

>Spring Profiles는 애플리케이션 설정을 특정 환경에서만 적용되게 하거나, 환경 별(local, test, production 등)로 다르게 적용할 때 사용된다.

#### 환경 마다 설정이 상이
* 개발 환경

```properties
spring.datasource.url=mysql://[개발환경IP]:3306/[개발DB]
spring.datasource.username=[DB접속 USER NAME]
spring.datasource.password=[DB접속 PASSWORD]
```
* 운영 환경

```properties
spring.datasource.url=mysql://[실제운영서버IP]:3306/[실제DB]
spring.datasource.username=[DB접속 USER NAME]
spring.datasource.password=[DB접속 PASSWORD]
```

## 환경에 따른  Profiles 전략
|  환경  | Profile    |
|:----:|:-----------|
테스트 | test
로컬 | local
개발서버 | develop
검증서버 | staging 
운영서버  | production 

* application.yml 
  * 공통으로 사용하는 설정
  * 내용이 많으면 사용 용도 단위로 분리될 수 있으나 통상 단일 파일로 사용함
* application_${profile}.yml
  * 환경마다 다르게 사용하는 섫정
    * application-test.yml
  * 내용이 많으면 사용 용도 단위로 분리될 수 있음
    * DB : application-develop-db.yml
    * redis : application-develop-redis.yml
    
#### application.yml
* 그룹을 사용하지 않을 경우  

```yaml
spring:
 config:
  activate:
   on-profile: test
```

* 그룹을 사용할 경우

```yaml
spring:
 config:
  activate:
   on-profile: test
  group:
    test:
      - test_db
      - test_redis
    local:
      - local_db
      - local_redis
```
* spring.config.activate.on-profiles 대신 -Dspring.profiles.active으로 활성화 할 수 있음
  * property 우선 순위에 따라 spring.config.activate.on-profiles가 오버라이딩 됨

https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html#configuration-metadata
https://godekdls.github.io/Spring%20Boot/howto.properties-and-configuration/

# Spring Boot DevTools

https://godekdls.github.io/Spring%20Boot/developing-with-spring-boot/#68-developer-tools

## 의존성 추가

* maven
```xml
dependency>
    <groupId>org.springframework.boot</groupId>
	   <artifactId>spring-boot-devtools</artifactId>
	  <scope>runtime</scope>
	  <optional>true</optional>
</dependency>
```

* gradle 

```groovy
developmentOnly("org.springframework.boot:spring-boot-devtools")
```

## Automatic Restart
* 파일 수정 후 저장을 하면, Classpath에 존재하는 파일의 변경을 감지하고, 자동으로 서버를 재시작
  * 설정을 통해 원하는 디렉터리만을 트리거로 설정할 수도 있음 
  
## Live Reload
* 소스에 변화가 있을 때 application이 자동으로 브라우저 새로 고침을 트리거 할 수 있게 해주는 프로토콜.
* livereload.com에서 Chrome, Firefox, Safari용 플러그인을 설치하여 사용할 수 있음
  * npm의 hot-reload-server처럼 새로고침없이 바로 갱신됩
  
## Global Settings
* spring에 대한 공통 설정을 할 수 있음.
* $HOME/.config/spring-boot 디렉토리에 공통 설정을 담아 놓으면 됩

## Remote Applications


