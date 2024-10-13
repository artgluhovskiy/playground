CREATE TABLE employee
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name  VARCHAR(256) NOT NULL,
    company_id BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE employee ADD CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(id);

INSERT INTO employee (first_name, last_name, company_id)
VALUES ('John', 'Doe', 1);
INSERT INTO employee (first_name, last_name, company_id)
VALUES ('Harry', 'Brown', 2);