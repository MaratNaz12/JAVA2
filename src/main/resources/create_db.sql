DROP SCHEMA public CASCADE;

CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_table_access_method = heap;

CREATE TABLE public.account (
    id bigint NOT NULL,
    curaccum bigint NOT NULL,
    curbalance bigint NOT NULL,
    curperiod bigint NOT NULL,
    bankacctype_id bigint,
    client_id bigint,
    office_id bigint
);

ALTER TABLE public.account OWNER TO postgres;

CREATE SEQUENCE public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.account_id_seq OWNER TO postgres;

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;

CREATE TABLE public.bankacctype (
    id bigint NOT NULL,
    dayperiod bigint NOT NULL,
    maxdaccrual bigint NOT NULL,
    maxdebit bigint NOT NULL,
    minaccrual bigint NOT NULL,
    mindebit bigint NOT NULL,
    name character varying(255) NOT NULL,
    profit double precision NOT NULL
);

ALTER TABLE public.bankacctype OWNER TO postgres;

CREATE SEQUENCE public.bankacctype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.bankacctype_id_seq OWNER TO postgres;

ALTER SEQUENCE public.bankacctype_id_seq OWNED BY public.bankacctype.id;

CREATE TABLE public.client (
    id bigint NOT NULL,
    address character varying(255),
    email character varying(255),
    name character varying(255) NOT NULL,
    telnumber1 character varying(255) NOT NULL,
    telnumber2 character varying(255)
);

ALTER TABLE public.client OWNER TO postgres;

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.client_id_seq OWNER TO postgres;

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;

CREATE TABLE public.clientoffice (
    id bigint NOT NULL,
    client bigint,
    office bigint
);

ALTER TABLE public.clientoffice OWNER TO postgres;

CREATE SEQUENCE public.clientoffice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.clientoffice_id_seq OWNER TO postgres;

ALTER SEQUENCE public.clientoffice_id_seq OWNED BY public.clientoffice.id;

CREATE TABLE public.office (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    telnumber1 character varying(255) NOT NULL,
    telnumber2 character varying(255) NOT NULL
);

ALTER TABLE public.office OWNER TO postgres;

CREATE SEQUENCE public.office_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.office_id_seq OWNER TO postgres;

ALTER SEQUENCE public.office_id_seq OWNED BY public.office.id;

CREATE TABLE public.operation (
    id bigint NOT NULL,
    dayperiod timestamp(6) without time zone NOT NULL,
    sum bigint NOT NULL,
    fromacc bigint,
    toacc bigint
);

ALTER TABLE public.operation OWNER TO postgres;

CREATE SEQUENCE public.operation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.operation_id_seq OWNER TO postgres;

ALTER SEQUENCE public.operation_id_seq OWNED BY public.operation.id;

CREATE TABLE public.testentity (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);

ALTER TABLE public.testentity OWNER TO postgres;

CREATE SEQUENCE public.testentity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.testentity_id_seq OWNER TO postgres;

ALTER SEQUENCE public.testentity_id_seq OWNED BY public.testentity.id;

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);

ALTER TABLE ONLY public.bankacctype ALTER COLUMN id SET DEFAULT nextval('public.bankacctype_id_seq'::regclass);

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);

ALTER TABLE ONLY public.clientoffice ALTER COLUMN id SET DEFAULT nextval('public.clientoffice_id_seq'::regclass);

ALTER TABLE ONLY public.office ALTER COLUMN id SET DEFAULT nextval('public.office_id_seq'::regclass);

ALTER TABLE ONLY public.operation ALTER COLUMN id SET DEFAULT nextval('public.operation_id_seq'::regclass);

ALTER TABLE ONLY public.testentity ALTER COLUMN id SET DEFAULT nextval('public.testentity_id_seq'::regclass);

SELECT pg_catalog.setval('public.account_id_seq', 1, false);

SELECT pg_catalog.setval('public.bankacctype_id_seq', 1, false);

SELECT pg_catalog.setval('public.client_id_seq', 1, false);

SELECT pg_catalog.setval('public.clientoffice_id_seq', 1, false);

SELECT pg_catalog.setval('public.office_id_seq', 1, false);

SELECT pg_catalog.setval('public.operation_id_seq', 1, false);

SELECT pg_catalog.setval('public.testentity_id_seq', 1, false);

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.bankacctype
    ADD CONSTRAINT bankacctype_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.clientoffice
    ADD CONSTRAINT clientoffice_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.testentity
    ADD CONSTRAINT testentity_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.operation
    ADD CONSTRAINT fk96h48rrbl3k07wokgubek65h7 FOREIGN KEY (toacc) REFERENCES public.account(id);
ALTER TABLE ONLY public.clientoffice
    ADD CONSTRAINT fkf6o823p54um1ex9vchb7ct766 FOREIGN KEY (client) REFERENCES public.client(id);
ALTER TABLE ONLY public.operation
    ADD CONSTRAINT fkjl5ixhs1b728xe8k9dc1ud5fl FOREIGN KEY (fromacc) REFERENCES public.account(id);
ALTER TABLE ONLY public.clientoffice
    ADD CONSTRAINT fkkc9jjfr951nv3qgq04k0tchs9 FOREIGN KEY (office) REFERENCES public.office(id);
ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkkm8yb63h4ownvnlrbwnadntyn FOREIGN KEY (client_id) REFERENCES public.client(id);
ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkpdk0damdo2ij7ti13ynfryt9t FOREIGN KEY (office_id) REFERENCES public.office(id);
ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkt57l9f830wehw0vsbykfaf1q1 FOREIGN KEY (bankacctype_id) REFERENCES public.bankacctype(id);
