CREATE TABLE student
(
    n_mec   BIGSERIAL       NOT NULL PRIMARY KEY,
    name   VARCHAR(255)     NOT NULL,
    email   VARCHAR(255),
    course  VARCHAR(255)    
);