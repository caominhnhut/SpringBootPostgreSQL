-- Create roles table
CREATE TABLE roles
(
    id         BIGSERIAL PRIMARY KEY,
    role_name  VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
