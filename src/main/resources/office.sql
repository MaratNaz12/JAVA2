CREATE TABLE office (
                        id SERIAL PRIMARY KEY ,
                        name VARCHAR(70) NOT NULL,
                        telnumber1 VARCHAR(15) NOT NULL,
                        telnumber2 VARCHAR(15) NOT NULL,
                        email VARCHAR(50) NOT NULL,
                        address VARCHAR(70) NOT NULL
);