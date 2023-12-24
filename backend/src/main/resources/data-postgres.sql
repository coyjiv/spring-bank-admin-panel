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
                                  age INT NOT NULL,
                                  phone VARCHAR(250) NOT NULL DEFAULT '0000000000',
                                  password VARCHAR(250) NOT NULL DEFAULT 'password',
                                  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create accounts table
CREATE TABLE public.accounts (
                                 id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                 number VARCHAR(250) NOT NULL,
                                 currency VARCHAR(3) NOT NULL,
                                 status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
                                 balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                                 customer_id INTEGER REFERENCES public.customers (id) NOT NULL,
                                 created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create employers table
CREATE TABLE public.employers (
                                  id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                  name VARCHAR(250) NOT NULL,
                                  address VARCHAR(250) NOT NULL,
                                  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create customerEmployment table
CREATE TABLE public.customerEmployment (
                                           id SERIAL PRIMARY KEY,  -- Changed from INTEGER AUTO_INCREMENT to SERIAL
                                           customer_id INTEGER REFERENCES public.customers (id) NOT NULL,  -- Added schema prefix to the referenced table
                                           employer_id INTEGER REFERENCES public.employers (id) NOT NULL,
                                           created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Insert data into customers
INSERT INTO public.customers (name, email, age) VALUES ('John', 'johndoe@mail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Jane', 'janedoe@mail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'bobdylan@mail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'alice4romw0nderland@mail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'snicker3242@funmail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'snicker5870@jokemail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'zany6490@laughmail.com', 33);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'droll1354@humormail.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'rollicking1662@jokemail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'amusing6703@humormail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'lighthearted1254@laughmail.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'zany4540@jokemail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'lighthearted9303@laughmail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Ella', 'zany5746@chuckle.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'giggle3079@humormail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'comic343@humormail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'jovial783@funmail.com', 27);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'zany1149@funmail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'funky2887@chuckle.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Jake', 'mirthful1648@chuckle.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'waggish6068@chuckle.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'snicker9570@jokemail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'comic3670@laughmail.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'droll8210@funmail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'lighthearted8561@jokemail.com', 33);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'mirthful9409@jokemail.com', 22);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'funky1714@funmail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'laughoutloud3906@humormail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'rollicking5809@funmail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'giggle452@chuckle.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'comic453@laughmail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'amusing7604@chuckle.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Jane', 'snicker885@jokemail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'giggle9762@laughmail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'merry4002@humormail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'laughoutloud1062@laughmail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'whimsical8593@laughmail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'droll3852@funmail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'amusing4530@laughmail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'chuckle3900@chuckle.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'mirthful670@chuckle.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'rollicking3145@chuckle.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Jake', 'laughoutloud2543@jokemail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'zany1783@chuckle.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'merry2233@humormail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'whimsical857@jokemail.com', 40);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'waggish8308@humormail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Jane', 'breezy4489@humormail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'zany8319@jokemail.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'laughoutloud7409@humormail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'funky3775@humormail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'funky1436@jokemail.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'whimsical8438@jokemail.com', 35);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'merry8899@laughmail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'giggle9166@chuckle.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'snicker7786@jokemail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Ella', 'zany1383@jokemail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'droll309@laughmail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Jane', 'giggle4866@jokemail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'whimsical272@humormail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'merry791@humormail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Jake', 'lighthearted4424@jokemail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'giggle5352@laughmail.com', 27);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'chortle644@chuckle.com', 27);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'chuckle3131@humormail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'comic1701@funmail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'chuckle3604@chuckle.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'amusing628@jokemail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'amusing9532@jokemail.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'jolly623@laughmail.com', 35);
INSERT INTO public.customers (name, email, age) VALUES ('Ivy', 'lighthearted462@laughmail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'quirky6542@chuckle.com', 27);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'snicker7957@funmail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Jake', 'chortle9196@funmail.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'funky9506@humormail.com', 31);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'whimsical2293@funmail.com', 22);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'quirky1986@funmail.com', 33);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'breezy7479@jokemail.com', 30);
INSERT INTO public.customers (name, email, age) VALUES ('Ella', 'quirky9375@jokemail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'amusing3888@jokemail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Jake', 'waggish1952@humormail.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'chortle8689@laughmail.com', 38);
INSERT INTO public.customers (name, email, age) VALUES ('Leo', 'mirthful8367@funmail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Zoe', 'merry3750@humormail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'mirthful4990@humormail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Tom', 'zany9242@chuckle.com', 29);
INSERT INTO public.customers (name, email, age) VALUES ('Alice', 'chortle5956@humormail.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'chuckle5664@jokemail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'giggle2017@jokemail.com', 42);
INSERT INTO public.customers (name, email, age) VALUES ('Ella', 'jolly5023@jokemail.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Max', 'funky9797@jokemail.com', 22);
INSERT INTO public.customers (name, email, age) VALUES ('Bob', 'laughoutloud7119@humormail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'snicker9743@laughmail.com', 33);
INSERT INTO public.customers (name, email, age) VALUES ('John', 'amusing1180@laughmail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Mia', 'breezy3365@humormail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Sam', 'giggle9169@laughmail.com', 20);
INSERT INTO public.customers (name, email, age) VALUES ('Ella', 'chortle7232@laughmail.com', 45);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'chuckle368@chuckle.com', 25);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'waggish5617@chuckle.com', 18);
INSERT INTO public.customers (name, email, age) VALUES ('Lily', 'whimsical677@jokemail.com', 31);


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
