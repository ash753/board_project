# 게시판 프로젝트

# Demo

[https://boardproject1.herokuapp.com/boards](https://boardproject1.herokuapp.com/boards)

# 목적

Spring 및 MyBatis를 활용한 BackEnd 설계능력 향상

# 기술 스택

- Java
- Spring Boot
- MyBatis
- MySQL
- Thymeleaf
- Bootstrap

# 기능 설명

- 게시글 작성, 조회, 수정, 삭제
- 댓글 작성, 삭제
- 파일 첨부기능

# 주요 특징

- 필수값에 대한 검증 적용- 제목, 본문, 댓글
- 다중 파일 첨부 기능
- Test Code Coverage 100% - Service, Respository


# UI 페이지 기획

- 게시글 목록

![image](https://user-images.githubusercontent.com/67199475/156622031-030d6821-e71d-4dd2-8184-c24c7ea9d8bb.png)

- 게시글 작성
    - 입력 검증 - 제목, 내용은 필수값입니다.
    - 파일 첨부 기능

![image](https://user-images.githubusercontent.com/67199475/156622227-2013b1fb-8640-4d97-a298-92e11623f40f.png)


- 게시글 수정
    - 제목은 수정이 불가능합니다.

![image](https://user-images.githubusercontent.com/67199475/156622282-17d731fb-fba7-453b-befb-cf1806282f4e.png)

- 게시글 상세 페이지
    - 첨부 파일 다운로드 기능
    - 댓글 작성 기능 - 댓글 내용은 필수값 입니다.

![image](https://user-images.githubusercontent.com/67199475/156622371-8a590b99-3bb4-4c2d-bd65-758bd5af43a6.png)


# 패키지 구조
![image](https://user-images.githubusercontent.com/67199475/155755096-22b7f21b-48be-4c72-90a6-bfac9708f524.png)

- controller : Client의 요청을 처리하는 Controller 클래스
- domain : dao 및 dto 클래스
- exception : 예외 클래스
- filemanagement : file을 folder에 저장 및 삭제하는 FileManagement 클래스
- mapper : DB 데이터를 처리하는 Mapper Interface
- repository : mapper를 사용하는 repository
- service : 게시글, 댓글, 파일 관련 service

# Backend 구조

## Route

[BoardController](https://www.notion.so/873ed81063374538b4a17e52bc719317)

![image](https://user-images.githubusercontent.com/67199475/155755257-8b4171ba-32dd-45d5-8477-c9eb165e0a1f.png)

[CommentController](https://www.notion.so/163851b2ebf34393a7b9fa7c85203c58)

![image](https://user-images.githubusercontent.com/67199475/155755743-6f897d3f-0634-4f76-bccd-26013d2d7efc.png)

[FileController](https://www.notion.so/92f6aef420bf4d729b63e27c877c91ef)

![image](https://user-images.githubusercontent.com/67199475/155755779-80696a93-5b72-49e7-a53e-4a20b2d41c21.png)


## Controller, Service, Respository 관계도

![image](https://user-images.githubusercontent.com/67199475/155755891-719a6d4a-ad4d-4469-b268-a5b7e438ae34.png)


## DB Schema
![image](https://user-images.githubusercontent.com/67199475/155755961-168b5afa-2145-4190-b275-da1235285a8a.png)


# 설치 방법

### Git Repository Clone

```
$ git clone https://github.com/ash753/board_project
$ cd board_project
```

## src/main/resources/application.properties 설정

![image](https://user-images.githubusercontent.com/67199475/155756027-2021b83a-4abe-43ef-add0-b6c44da7b9d2.png)

- Deployment 관련 설정 제거

![image](https://user-images.githubusercontent.com/67199475/155756060-7a441c9f-f565-4094-9fb2-de35280db641.png)

- spring.datasource 설정 : 자신의 DB에 맞는 url, username, password 입력
- file.dir 설정 : 파일이 저장될 위치를 절대경로로 입력

## Maven Packaging

```
$ ./mvnw clean package
```

# 실행 방법

```
$ cd target
$ java -jar boardProject-0.0.1-SNAPSHOT.jar
```

# 향후 추가 기능

- 페이징
- 로그인
