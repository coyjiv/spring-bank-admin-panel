-- Drop tables if they exist
DROP TABLE IF EXISTS customerEmployment CASCADE;
DROP TABLE IF EXISTS employers CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS customers CASCADE;

-- Create customers table
CREATE TABLE public.customers (
                                  id SERIAL PRIMARY KEY,  -- Changed from INT AUTO_INCREMENT to SERIAL
                                  name VARCHAR(250) NOT NULL,
                                  email VARCHAR(250) NOT NULL,
                                  age INT NOT NULL
);

-- Create accounts table
CREATE TABLE public.accounts (
                                 id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                 number VARCHAR(250) NOT NULL,
                                 currency VARCHAR(3) NOT NULL,
                                 balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                                 customer_id INTEGER REFERENCES public.customers (id) NOT NULL  -- Added schema prefix to the referenced table
);

-- Create employers table
CREATE TABLE public.employers (
                                  id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                  name VARCHAR(250) NOT NULL,
                                  address VARCHAR(250) NOT NULL
);

-- Create customerEmployment table
CREATE TABLE public.customerEmployment (
                                           id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                           customer_id INTEGER REFERENCES public.customers (id) NOT NULL,  -- Added schema prefix to the referenced table
                                           employer_id INTEGER REFERENCES public.employers (id) NOT NULL  -- Added schema prefix to the referenced table
);

-- Insert data into customers
INSERT INTO public.customers (name, email, age) VALUES ('John', 'johndoe@mail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Jane', 'janedoe@mail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'bobdylan@mail.com', 40);

-- Insert data into accounts
INSERT INTO public.accounts (number, currency, balance, customer_id) VALUES ('123456789', 'USD', 1000, 1);
INSERT INTO public.accounts (number, currency, balance, customer_id) VALUES ('987654321', 'EUR', 2000, 1);
INSERT INTO public.accounts (number, currency, balance, customer_id) VALUES ('123123123', 'USD', 3000, 2);
INSERT INTO public.accounts (number, currency, balance, customer_id) VALUES ('321321321', 'EUR', 4000, 2);

-- Insert data into employers
INSERT INTO public.employers (name, address) VALUES ('Google', 'Mountain View, CA');
INSERT INTO public.employers (name, address) VALUES ('Facebook', 'Menlo Park, CA');
INSERT INTO public.employers (name, address) VALUES ('Amazon', 'Seattle, WA');

-- Insert data into customerEmployment
INSERT INTO public.customerEmployment (customer_id, employer_id) VALUES (1, 1);
INSERT INTO public.customerEmployment (customer_id, employer_id) VALUES (2, 2);
INSERT INTO public.customerEmployment (customer_id, employer_id) VALUES (3, 3);
