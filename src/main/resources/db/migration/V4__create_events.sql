CREATE TABLE IF NOT EXISTS events (
id BIGINT PRIMARY KEY,
title VARCHAR(150),
description TEXT,
start_time TIMESTAMP,
end_time TIMESTAMP,
group_id INT,
created_by INT,
created_at TIMESTAMP
);
