
DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE public.customers (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) NOT NULL,
                               age INT NOT NULL
);

DROP TABLE IF EXISTS accounts CASCADE;
CREATE TABLE public.accounts (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(250) NOT NULL,
                       currency VARCHAR(3) NOT NULL,
                       balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                       customer_id INTEGER REFERENCES customers (id) NOT NULL
);

DROP TABLE IF EXISTS employers CASCADE;
CREATE TABLE public.employers (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250) NOT NULL,
                       address VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS customerEmployment CASCADE;
CREATE TABLE public.customerEmployment (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       customer_id INTEGER REFERENCES customers (id) NOT NULL,
                       employer_id INTEGER REFERENCES employers (id) NOT NULL
);

INSERT INTO customers (name, email, age) VALUES ('John', 'johndoe@mail.com', 25);
INSERT INTO customers (name, email, age) VALUES ('Jane', 'janedoe@mail.com', 30);
INSERT INTO customers (name, email, age) VALUES ('Bob', 'bobdylan@mail.com', 40);

INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('123456789', 'USD', 1000, 1);
INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('987654321', 'EUR', 2000, 1);
INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('123123123', 'USD', 3000, 2);
INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('321321321', 'EUR', 4000, 2);

INSERT INTO employers (name, address) VALUES ('Google', 'Mountain View, CA');
INSERT INTO employers (name, address) VALUES ('Facebook', 'Menlo Park, CA');
INSERT INTO employers (name, address) VALUES ('Amazon', 'Seattle, WA');

INSERT INTO customerEmployment (customer_id, employer_id) VALUES (1, 1);
INSERT INTO customerEmployment (customer_id, employer_id) VALUES (2, 2);
INSERT INTO customerEmployment (customer_id, employer_id) VALUES (3, 3);
