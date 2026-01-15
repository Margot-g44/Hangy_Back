CREATE TABLE IF NOT EXISTS members (
user_id BIGINT,
group_id BIGINT,
role VARCHAR(20),
joined_at TIMESTAMP,

PRIMARY KEY (user_id, group_id)
);
