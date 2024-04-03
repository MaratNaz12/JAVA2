CREATE TABLE bankacctype (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(70) NOT NULL,
                             profit FLOAT NOT NULL,
                             dayperiod INT NOT NULL,
                             maxdebit INT NOT NULL,
                             mindebit INT NOT NULL,
                             maxaccruel INT NOT NULL,
                             minaccrual INT NOT NULL
);