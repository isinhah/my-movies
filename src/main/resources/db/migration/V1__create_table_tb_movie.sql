CREATE TABLE IF NOT EXISTS tb_movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    rating INTEGER,
    review VARCHAR(255),
    log_date TIMESTAMP
);