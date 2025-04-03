# 테마별 집중 스케줄 앱 API 명세서

## 기본 URL

``` bash
/api/schedules
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



