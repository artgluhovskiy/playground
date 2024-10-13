CREATE TABLE company
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL
);

ALTER TABLE company ADD CONSTRAINT pk_company PRIMARY KEY (id);

INSERT INTO company (id, name, email)
VALUES (1, 'Company_1', 'company_1@gmail.com');
INSERT INTO company (id, name, email)
VALUES (2, 'Company_2', 'company_2@gmail.com');