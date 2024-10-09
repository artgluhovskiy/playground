CREATE TABLE employee
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name  VARCHAR(256) NOT NULL
);

INSERT INTO employee (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO employee (first_name, last_name) VALUES ('Harry', 'Brown');