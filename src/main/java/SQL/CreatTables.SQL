CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY ,
    user_name VARCHAR(255) NOT NULL ,
    user_username VARCHAR(255) UNIQUE NOT NULL ,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS admins(
    admin_id SERIAL PRIMARY KEY ,
    admin_name VARCHAR(255) NOT NULL ,
    admin_username VARCHAR(255) UNIQUE NOT NULL ,
    admin_password VARCHAR(255) NOT NULL
);