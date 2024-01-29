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
CREATE TABLE IF NOT EXISTS branch(
    branch_id SERIAL PRIMARY KEY ,
    branch_name VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS subbranch(
    subbranch_id SERIAL PRIMARY KEY ,
    subbranch_name VARCHAR(255) UNIQUE NOT NULL ,
    branch_id_fk INTEGER REFERENCES branch(branch_id)
);
CREATE TABLE IF NOT EXISTS products(
    products_id SERIAL PRIMARY KEY ,
    products_name VARCHAR(255) NOT NULL ,
    products_price FLOAT NOT NULL ,
    products_number INTEGER NOT NULL ,
    subbranch_id_fk INTEGER REFERENCES subbranch(subbranch_id)
);