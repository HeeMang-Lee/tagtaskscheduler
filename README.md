# 테마별 집중 스케줄 앱 (Focused Schedule API)

## 프로젝트 개요

이 프로젝트는 사용자의 집중 테마(Deep Work, Study, Creative 등)에 맞춰 할 일을 관리할 수 있도록 설계된 RESTful API 서버입니다. 사용자는 일정 날짜, 할 일 내용, 집중 태그(focusTag)를 등록하고, 원하는 조건(작성자 ID, 태그, 날짜 등)으로 일정을 조회, 수정, 삭제할 수 있습니다.

기본적인 일정 CRUD 기능 외에도 작성자 정보를 Author 테이블로 분리하여 일정과 연관관계를 설정하였으며, 로그인 기능을 통해 인증 기반 API 사용도 가능하도록 구현되었습니다. JPA를 중심으로 3 Layer Architecture, DTO 기반 응답 설계, 예외 처리 전략 등을 학습하는 것을 목표로 진행되었습니다.

---

## 주요 기능

- 회원 가입 및 로그인 (세션 기반 인증)
- 일정 등록 (작성자와 연관, 집중 태그 포함)
- 전체 일정 조건 조회 (작성자 ID, FocusTag, 수정일 등 필터)
- 일정 단건 조회
- 일정 수정 및 삭제
- 입력값 유효성 검증 처리 (필수값, 이메일 형식, 글자 수 제한 등)
- 전역 예외 처리 적용 (`@ControllerAdvice`, `CustomException` 등)

---

## 기술 스택

- Java 17
- Spring Boot 3
- Spring Data JPA + MySQL
- Lombok
- Gradle (Build Tool)
- Postman (API 테스트)

---

## 프로젝트 구조 (도메인 기반 3 Layer Architecture)

com.example.project   
├── common # 공통 코드 (BaseEntity, 예외 등)  
├── config # 설정 관련 (Filter 등록, Encoder 등)  
├── auth # 인증 도메인 (컨트롤러, DTO, 필터)  
├── author # 작성자 도메인  
│ ├── controller  
│ ├── dto  
│ ├── repository   
│ └── service    
├── schedule # 일정 도메인  
│ ├── controller  
│ ├── dto  
│ ├── repository  
│ └── service  
└── ScheduleApplication.java  

---

## 설계 및 개발 포인트

### 1. DTO를 통한 응답 일관성 유지

- 모든 API 응답은 Entity가 아닌 DTO를 통해 반환
- 계층 간 책임 분리, 응답 포맷 일관성 확보

### 2. 작성자와 일정 간 연관관계 구현 (1:N)

- 일정(Schedule)은 작성자(Author)의 ID를 외래키로 저장
- 응답 DTO에는 작성자의 이름, 이메일이 함께 포함됨

### 3. FocusTag Enum 도입

- 일정 등록 시 사용자가 선택한 집중 태그를 ENUM으로 관리 (e.g., DEEP_WORK, STUDY 등)
- Enum 기반 유효성 검증 적용

### 4. 전역 예외 처리 구조 도입

- `ErrorCode` Enum + `CustomException` + `GlobalExceptionHandler`
- Bean Validation 실패, 인증 실패, 도메인 예외 등 일관된 JSON 응답 처리

### 5. 필터 기반 세션 인증

- `LoginFilter`를 통해 인증이 필요한 URL 접근 시 세션 검증
- 로그인/회원가입/루트 URL은 화이트리스트로 설정하여 인증 제외

---
## 트러블 슈팅

링크 : https://pear-bagel-027.notion.site/Spring-Task-Focus-Scheduler-API-1cbf73098d8580aaa25cfd9fa335725c?pvs=74

---
## 개발 완료 상태

- [x] 회원가입 / 로그인 / 로그아웃 구현
- [x] 세션 기반 인증 및 인증 필터 적용
- [x] 일정 등록/조회/수정/삭제 API 완성
- [x] FocusTag Enum 기반 일정 필터링 구현
- [x] 유효성 검증 및 전역 예외 처리 적용
- [x] ERD 설계 및 API 명세서 정리 완료
- [x] Postman 테스트 완료 및 문서화

# 테마별 집중 스케줄 앱 API 명세서

## 기본 URL

``` bash
/api
```  
---
## 1️. 일정 생성
Method: POST  
URL: /api/schedules  
필수값: scheduleDate, taskContent, focusTag, authorId

요청 본문 예시:

``` json
{
    "scheduleDate" : "2025-04-02",
    "taskContent": "블로그 작성 및 회고 정리",
    "focusTag": "DEEP_WORK",
    "authorId": 1,
}
```
응답:
``` json
201 Created
{
    "id": 1,
    "scheduleDate": "2025-04-02",
    "taskContent": "블로그 작성 및 회고 정리",
    "focusTag": "DEEP_WORK",
    "author": {
    "id": 1,
    "name": "이희망",
    "email": "lee@naver.com"
},
    "createdAt": "2025-04-02T10:00:00",
    "modifiedAt": "2025-04-02T10:00:00"
}
```
---
## 2️. 전체 일정 조회
Method: GET  
URL: /api/schedules  
쿼리 파라미터 (선택): modifiedAt=2025-04-02, authorId=1, focusTag=CREATIVE

응답:

```json
200 OK
[
  {
    "id": 1,
    "scheduleDate": "2025-04-02",
    "taskContent": "블로그 작성 및 회고 정리",
    "focusTag": "DEEP_WORK",
    "author": {
      "id": 1,
      "name": "이희망",
      "email": "lee@naver.com"
    },
    "createdAt": "2025-04-02T10:00:00",
    "modifiedAt": "2025-04-02T10:00:00"
  }
]
```
---
## 3️. 단일 일정 조회
Method: GET  
URL: /api/schedules/{id}

응답:

```json
200 OK
{
    "id": 1,
    "scheduleDate": "2025-04-02",
    "taskContent": "블로그 작성 및 회고 정리",
    "focusTag": "DEEP_WORK",
    "author": {
        "id": 1,
        "name": "이희망",
        "email": "lee@naver.com"
    },
    "createdAt": "2025-04-02T10:00:00",
    "modifiedAt": "2025-04-02T10:00:00"
}
```
---
## 4️. 일정 수정
Method: PUT  
URL: /api/schedules/{id}  
필수값: scheduleDate, taskContent, focusTag, authorId

요청 예시:

```json
{
    "scheduleDate": "2025-04-03",
    "taskContent": "API 명세 작성 마무리",
    "focusTag": "STUDY",
    "authorId": 1,
}
```
응답:

```json
200 OK
{
    "id": 1,
    "scheduleDate": "2025-04-03",
    "taskContent": "API 명세 작성 마무리",
    "focusTag": "STUDY",
    "author": {
        "id": 1,
        "name": "이희망",
        "email": "lee@naver.com"
    },
    "createdAt": "2025-04-02T10:00:00",
    "modifiedAt": "2025-04-03T12:00:00"
}
```
---
## 5️. 일정 삭제
Method: DELETE  
URL: /api/schedules/{id}

응답:

```json
204 No Content
```
---
## 6 . 작성자 등록  

Method: POST  
URL: /api/authors  
필수값: name,email,password

요청 본문 예시
```json
{
  "name": "이희망",
  "email": "lee@naver.com",
  "password": "1234"
}
```  
응답:
```json
201 Created
{
  "id": 1,
  "name": "이희망",
  "email": "lee@naver.com",
  "createdAt": "2025-03-24T15:00:00",
  "modifiedAt": "2025-03-24T15:00:00"
}
```

---
## 7. 유저 조회(단건)  

Method:GET  
URL:/api/authors/{id}
```json
200 OK
{
  "id": 1,
  "name": "이희망",
  "email": "lee@naver.com",
  "createdAt": "2025-04-03T10:30:00",
  "modifiedAt": "2025-04-03T10:30:00"
}
```
---
## 8. 유저 비밀번호 수정  

Method:PATCH  
URL:/api/authors/{id}/password  
필수값 : oldPassword,newPassword

요청
```json
{
  "oldPassword": "1234",
  "newPassword": "5678"
}
```

응답
```json
200 OK
{
  "message": "비밀번호가 성공적으로 변경되었습니다."
}
```
---
## 9. 유저 삭제

Method:DELETE
URL:/api/authors/{id}
```json
204 No Content
```
---

## 10. 로그인

Method: POST  
URL: /api/auth/login  
선행조건 : 작성자 등록 후 로그인

요청
```json
  "email": "lee@naver.com",
  "password": "1234"
```

응답
```json
200 OK

"로그인 성공"
```

예외 응답  

- 이메일 미존재
```json
{
  "status": 401,
  "message": "등록되지 않은 이메일입니다."
}
```

- 비밀번호 불일치
```json
{
  "status": 401,
  "message": "비밀번호가 일치하지 않습니다."
}
```
---
## 11. 로그아웃

Method : POST  
URL : /api/auth/logout

응답
```json
200 OK

"로그아웃 성공"
```
---
##  필드 유효성 규칙

| 필드명        | DTO 위치                             | 어노테이션                        | 설명                              |
|---------------|--------------------------------------|----------------------------------|-----------------------------------|
| `name`        | `AuthorRequestDto`                   | `@NotBlank`                      | 이름은 공백 없이 필수              |
| `email`       | `AuthorRequestDto`, `LoginRequestDto` | `@NotBlank`, `@Email`            | 이메일은 공백 없이 필수, 형식 검사 포함 |
| `password`    | `AuthorRequestDto`, `LoginRequestDto`, `UpdatePasswordRequestDto` | `@NotBlank` | 비밀번호는 공백 없이 필수         |
| `oldPassword` | `UpdatePasswordRequestDto`           | `@NotBlank`                      | 기존 비밀번호는 공백 없이 필수     |
| `newPassword` | `UpdatePasswordRequestDto`           | `@NotBlank`                      | 새로운 비밀번호는 공백 없이 필수   |
| `scheduleDate`| `ScheduleRequestDto`                 | `@NotBlank`                      | 일정 날짜는 공백 없이 필수         |
| `taskContent` | `ScheduleRequestDto`                 | `@NotBlank`                      | 할 일 내용은 공백 없이 필수        |
| `focusTag`    | `ScheduleRequestDto`                 | `@NotNull`                       | 집중 태그(FocusTag)는 필수         |
| `authorId`    | `ScheduleRequestDto`                 | `@NotNull`                       | 작성자 ID는 필수                   |

---
# ERD 설계 (Focus Mode 버전)

```pgsql
┌────────────────────────┐         ┌──────────────────────┐
│        Author          │         │      Schedule        │
├────────────────────────┤         ├──────────────────────┤
│ id (PK)                │◄────────┤ author_id (FK)       │
│ name                   │         │ id (PK)              │
│ email                  │         │ schedule_date        │
│ password               │         │ task_content         │
│ created_at             │         │ focus_tag (ENUM)     │
│ modified_at            │         │ created_at           │
└────────────────────────┘         │ modified_at          │
                                   └──────────────────────┘
```


## Schedule Table 상세

| 컬럼명         | 타입      | 제약 조건                                      |
|----------------|-----------|-----------------------------------------------|
| `id`           | BIGINT    | PK, AUTO_INCREMENT                            |
| `author_id`    | BIGINT    | FK → `author.id`, NOT NULL                    |
| `schedule_date`| VARCHAR   | NOT NULL                                      |
| `task_content` | TEXT      | NOT NULL                                      |
| `focus_tag`    | ENUM      | NOT NULL (e.g., `DEEP_WORK` 등으로 제한)      |
| `created_at`   | DATETIME  | NOT NULL                                      |
| `modified_at`  | DATETIME  | NOT NULL                                      |

## Author Table 상세

| 컬럼명       | 타입          | 제약 조건               |
|--------------|---------------|--------------------------|
| `id`         | BIGINT        | PK, AUTO_INCREMENT       |
| `name`       | VARCHAR(100)  | NOT NULL                 |
| `email`      | VARCHAR(255)  | NOT NULL, UNIQUE         |
| `password`   | VARCHAR(255)  | NOT NULL                 |
| `created_at` | TIMESTAMP     | NOT NULL                 |
| `modified_at`| TIMESTAMP     | NOT NULL                 |



