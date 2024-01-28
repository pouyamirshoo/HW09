CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY ,
    user_name VARCHAR(255) NOT NULL ,
    user_username VARCHAR(255) UNIQUE NOT NULL ,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255)
);