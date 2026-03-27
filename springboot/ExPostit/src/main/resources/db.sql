USE mydb;

CREATE TABLE memo (
                      id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                      content TEXT NOT NULL,              -- 메모 내용
                      color   VARCHAR(20) NOT NULL DEFAULT 'yellow',  -- NOTE_COLORS 의 name 값
                      pos_x   INT NOT NULL DEFAULT 0,     -- x 좌표
                      pos_y   INT NOT NULL DEFAULT 0,     -- y 좌표
                      z_index INT NOT NULL DEFAULT 0,     -- zIndex
                      rotation DECIMAL(5,2) NOT NULL DEFAULT 0.00,    -- 회전 각도 (-4.00 ~ 4.00 등)
                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
                          ON UPDATE CURRENT_TIMESTAMP,
                      PRIMARY KEY (id),
                      INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO memo (content, color, pos_x, pos_y, z_index, rotation)
VALUES
    ('오늘 할 일\n- 메모 정리\n- 회의 준비', 'yellow', 80,  90,  2, -2.40),
    ('아이디어\n포스트잇들을 주제별로 묶기',   'blue',   380, 140, 3,  3.10),
    ('여기에 메모를 입력하세요...',          'pink',   190, 360, 1, -1.10);

SELECT * FROM memo;