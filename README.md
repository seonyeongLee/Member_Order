## Member_Order
  * 회원 & 주문 관련 API

<br/>

## 요구 사항

* 클라이언트(ios, android, web app)에서 사용할 다음 API를 개발
    - 회원 가입
    - 회원 로그인(인증)
    - 회원 로그아웃
    - 단일 회원 상세 정보 조회
    - 단일 회원의 주문 목록 조회
    - 여러 회원 목록 조회 :
        - 페이지네이션으로 일정 단위로 조회합니다.
        - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
        - 각 회원의 마지막 주문 정보

* API를 쉽게 이해할 수 있는 문서를 작성

* 데이터베이스는 MySQL을 사용
    - MySQL 엔진은 innodb로 되어 있습니다.
    - MySQL은 쓰기 전용 서버와 읽기 전용 서버로 replication 설정이 되어 있습니다.
    - MySQL을 구성할 필요는 없습니다.

* 테이블 생성 쿼리문도 소스코드에 포함 ( query.sql )

<br/>

## 개발환경
* Java (11)
* Spring Boot (2.4.5)
* Gradle (6.8.3)
* MySQL (8.0)
* lombok (1.18.6)
* JPA
* swagger-ui (2.9.2)

<br/>

* IntelliJ

<br/>

## 설치/빌드 방법

    $ git clone https://github.com/seonyeongLee/Member_Order.git

    $ cd ./Member_Order
    
    $ ./gradlew bootrun
