# 테마별 집중 스케줄 앱 API 명세서

## 기본 URL

``` bash
/api/schedules
```  
---
## 1️. 일정 생성
Method: POST  
URL: /api/schedules  
필수값: scheduleDate, taskContent, focusTag, authorId, password

요청 본문 예시:

``` json
{
    "scheduleDate" : "2025-04-02",
    "taskContent": "블로그 작성 및 회고 정리",
    "focusTag": "DEEP_WORK",
    "authorId": 1,
    "password": "1234"
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
필수값: scheduleDate, taskContent, focusTag, authorId, password

요청 예시:

```json
{
    "scheduleDate": "2025-04-03",
    "taskContent": "API 명세 작성 마무리",
    "focusTag": "STUDY",
    "authorId": 1,
    "password": "1234"
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

요청:

```json
{
  "password": "1234"
}
```
응답:

```json
204 No Content
```
---
## 6 . 작성자 등록  

기존과 동일 (변경 없음)

---
## 필드 유효성 규칙
- scheduleDate: 필수

- taskContent: 필수, 공백 불가

- focusTag: 필수 (Enum: DEEP_WORK, STUDY, CREATIVE, REST, ETC 등)

- authorId: 필수

- password: 필수

# ERD 설계 (Focus Mode 버전)

```pgsql
┌────────────────────┐         ┌──────────────────────┐
│      Author        │         │      Schedule        │
├────────────────────┤         ├──────────────────────┤
│ id (PK)            │◄────────┤ author_id (FK)       │
│ name               │         │ id (PK)              │
│ email              │         │ schedule_date        │
│ created_at         │         │ task_content         │
│ modified_at        │         │ focus_tag (ENUM)     │
└────────────────────┘         │ password             │
                               │ created_at           │
                               │ modified_at          │
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
| `password`     | VARCHAR   | NOT NULL                                      |
| `created_at`   | DATETIME  | NOT NULL                                      |
| `modified_at`  | DATETIME  | NOT NULL                                      |

## Author Table 상세

| 컬럼명       | 타입     | 제약 조건         |
|--------------|----------|------------------|
| `id`         | BIGINT   | PK, AUTO_INCREMENT |
| `name`       | VARCHAR  | NOT NULL         |
| `email`      | VARCHAR  | NOT NULL, UNIQUE |
| `created_at` | DATETIME | NOT NULL         |
| `modified_at`| DATETIME | NOT NULL         |


