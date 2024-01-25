-- Drop tables if they exist
DROP TABLE IF EXISTS customerEmployment CASCADE;
DROP TABLE IF EXISTS employers CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS customers CASCADE;

-- Create customers table
CREATE TABLE public.customers (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(250) NOT NULL,
                                  email VARCHAR(250) NOT NULL,
                                  age INT NOT NULL,
                                  phone VARCHAR(250) NOT NULL DEFAULT '0000000000',
                                  password VARCHAR(250) NOT NULL DEFAULT 'password',
                                  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- Create accounts table
CREATE TABLE public.accounts (
                                 id SERIAL PRIMARY KEY,
                                 number VARCHAR(250) NOT NULL,
                                 currency VARCHAR(3) NOT NULL,
                                 status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
                                 balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                                 customer_id INTEGER REFERENCES public.customers (id) NOT NULL,
                                 created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create employers table
CREATE TABLE public.employers (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(250) NOT NULL,
                                  address VARCHAR(250) NOT NULL,
                                  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create customerEmployment table
CREATE TABLE public.customerEmployment (
                                           id SERIAL PRIMARY KEY,
                                           customer_id INTEGER REFERENCES public.customers (id) NOT NULL,
                                           employer_id INTEGER REFERENCES public.employers (id) NOT NULL,
                                           created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
