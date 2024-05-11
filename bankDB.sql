--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.0

-- Started on 2024-05-11 11:43:27

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

--
-- TOC entry 218 (class 1259 OID 16577)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    account_id integer NOT NULL,
    currency character varying(10),
    balance integer,
    type character varying(50),
    user_id integer
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16576)
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.account_account_id_seq OWNER TO postgres;

--
-- TOC entry 4807 (class 0 OID 0)
-- Dependencies: 217
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_account_id_seq OWNED BY public.account.account_id;


--
-- TOC entry 220 (class 1259 OID 16584)
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    transaction_id integer NOT NULL,
    date date,
    amount character varying,
    origin_account_id integer,
    destiny_account_id integer
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16583)
-- Name: transaction_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transaction_transaction_id_seq OWNER TO postgres;

--
-- TOC entry 4808 (class 0 OID 0)
-- Dependencies: 219
-- Name: transaction_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_transaction_id_seq OWNED BY public.transaction.transaction_id;


--
-- TOC entry 216 (class 1259 OID 16568)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    name character varying(255),
    last_name character varying(255),
    user_name character varying(255),
    password character varying(255),
    "isAdmin" smallint
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16567)
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_user_id_seq OWNER TO postgres;

--
-- TOC entry 4809 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;


--
-- TOC entry 4645 (class 2604 OID 16580)
-- Name: account account_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account ALTER COLUMN account_id SET DEFAULT nextval('public.account_account_id_seq'::regclass);


--
-- TOC entry 4646 (class 2604 OID 16587)
-- Name: transaction transaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN transaction_id SET DEFAULT nextval('public.transaction_transaction_id_seq'::regclass);


--
-- TOC entry 4644 (class 2604 OID 16571)
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);


--
-- TOC entry 4799 (class 0 OID 16577)
-- Dependencies: 218
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account VALUES (2, '$', 136700, 'investment', 8);
INSERT INTO public.account VALUES (3, '$', 49300, 'savings', 10);
INSERT INTO public.account VALUES (4, 'US$', 10150, 'savings', 8);
INSERT INTO public.account VALUES (5, 'US$', 3350, 'savings', 10);
INSERT INTO public.account VALUES (7, 'US$', 0, 'investment', 9);
INSERT INTO public.account VALUES (6, '$', 15000, 'savings', 9);
INSERT INTO public.account VALUES (1, '$', 60440, 'savings', 8);


--
-- TOC entry 4801 (class 0 OID 16584)
-- Dependencies: 220
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaction VALUES (1, '2022-09-19', '4560', 1, 2);
INSERT INTO public.transaction VALUES (2, '2022-09-19', '18000', 1, 3);
INSERT INTO public.transaction VALUES (3, '2022-09-19', '1350', 4, 5);
INSERT INTO public.transaction VALUES (4, '2022-09-19', '200', 3, 2);
INSERT INTO public.transaction VALUES (5, '2022-09-19', '1000', 3, 2);
INSERT INTO public.transaction VALUES (6, '2022-09-19', '2000', 4, 5);
INSERT INTO public.transaction VALUES (7, '2022-09-19', '45000', 1, 3);
INSERT INTO public.transaction VALUES (8, '2022-09-26', '12000', 3, 2);
INSERT INTO public.transaction VALUES (9, '2022-09-27', '500', 3, 2);
INSERT INTO public.transaction VALUES (10, '2024-05-11', '5000', 6, 1);


--
-- TOC entry 4797 (class 0 OID 16568)
-- Dependencies: 216
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES (1, 'ale', 'zdut', 'admin', 'admin', NULL);
INSERT INTO public."user" VALUES (2, 'ale', 'perez', 'ale', '1234', NULL);
INSERT INTO public."user" VALUES (3, 'Mario', 'Pergolini', 'mario_pergolini', '1234', NULL);
INSERT INTO public."user" VALUES (4, 'seba', 'dlh', 'root', 'password', 1);
INSERT INTO public."user" VALUES (5, 'Tomas', 'Temporelli', 'tototempo', '123456', NULL);


--
-- TOC entry 4810 (class 0 OID 0)
-- Dependencies: 217
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_account_id_seq', 7, true);


--
-- TOC entry 4811 (class 0 OID 0)
-- Dependencies: 219
-- Name: transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_transaction_id_seq', 10, true);


--
-- TOC entry 4812 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_user_id_seq', 5, true);


--
-- TOC entry 4650 (class 2606 OID 16582)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 4652 (class 2606 OID 16591)
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id);


--
-- TOC entry 4648 (class 2606 OID 16575)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


-- Completed on 2024-05-11 11:43:27

--
-- PostgreSQL database dump complete
--

