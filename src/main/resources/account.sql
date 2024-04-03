CREATE TABLE Account(
                        Id serial PRIMARY KEY,
                        ClientId integer NOT NULL,
                        OfficeId integer NOT NULL,
                        AccType  integer NOT NUll,
                        CurBalance int NOT NULL,
                        CurPeriod int NOT NULL,
                        CurAccum int Not NULL,
                        FOREIGN KEY (ClientId) REFERENCES Client(Id),
                        FOREIGN KEY (OfficeId) REFERENCES Office(Id),
                        FOREIGN KEY (Acctype) REFERENCES BankAccType(Id)

);