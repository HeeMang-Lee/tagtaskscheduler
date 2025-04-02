-- author 테이블 생성
CREATE TABLE author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        created_at DATETIME NOT NULL,
                        modified_at DATETIME NOT NULL
);

-- schedule 테이블 생성
CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          author_id BIGINT NOT NULL,
                          schedule_date VARCHAR(100) NOT NULL,
                          task_content TEXT NOT NULL,
                          focus_tag ENUM('DEEP_WORK', 'STUDY', 'CREATIVE', 'REST', 'ETC') NOT NULL,
                          created_at DATETIME NOT NULL,
                          modified_at DATETIME NOT NULL,
                          CONSTRAINT fk_schedule_author FOREIGN KEY (author_id) REFERENCES author(id)
);
