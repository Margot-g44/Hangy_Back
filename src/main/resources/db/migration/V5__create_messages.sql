CREATE TABLE IF NOT EXISTS messages (
    id BIGINT PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMP,
    user_id INT,
    group_id INT
);
