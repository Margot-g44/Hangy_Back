ALTER TABLE users
ADD CONSTRAINT uq_users_email UNIQUE (email);

ALTER TABLE users
ADD CONSTRAINT uq_users_username UNIQUE (username);


ALTER TABLE groups
ADD CONSTRAINT fk_groups_owner
FOREIGN KEY (owner_id)
REFERENCES users(id)
ON DELETE CASCADE;


ALTER TABLE members
ADD CONSTRAINT fk_members_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;

ALTER TABLE members
ADD CONSTRAINT fk_members_group
FOREIGN KEY (group_id)
REFERENCES groups(id)
ON DELETE CASCADE;

ALTER TABLE members
ADD CONSTRAINT chk_members_role
CHECK (role IN ('admin', 'member'));



ALTER TABLE events
ADD CONSTRAINT fk_events_group
FOREIGN KEY (group_id)
REFERENCES groups(id)
ON DELETE CASCADE;

ALTER TABLE events
ADD CONSTRAINT fk_events_creator
FOREIGN KEY (created_by)
REFERENCES users(id)
ON DELETE SET NULL;

ALTER TABLE events
ADD CONSTRAINT chk_events_time
CHECK (end_time > start_time);


ALTER TABLE messages
ADD CONSTRAINT fk_messages_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;

ALTER TABLE messages
ADD CONSTRAINT fk_messages_group
FOREIGN KEY (group_id)
REFERENCES groups(id)
ON DELETE CASCADE;
