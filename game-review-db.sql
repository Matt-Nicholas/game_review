--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: game_table; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE game_table (
    id integer NOT NULL,
    title character varying,
    summary character varying
);


ALTER TABLE game_table OWNER TO "Guest";

--
-- Name: game_table_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE game_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game_table_id_seq OWNER TO "Guest";

--
-- Name: game_table_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE game_table_id_seq OWNED BY game_table.id;


--
-- Name: genre_game_link; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE genre_game_link (
    id integer NOT NULL,
    genre_id integer,
    game_id integer
);


ALTER TABLE genre_game_link OWNER TO "Guest";

--
-- Name: genre_game_link_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE genre_game_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE genre_game_link_id_seq OWNER TO "Guest";

--
-- Name: genre_game_link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE genre_game_link_id_seq OWNED BY genre_game_link.id;


--
-- Name: genre_table; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE genre_table (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE genre_table OWNER TO "Guest";

--
-- Name: genre_table_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE genre_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE genre_table_id_seq OWNER TO "Guest";

--
-- Name: genre_table_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE genre_table_id_seq OWNED BY genre_table.id;


--
-- Name: review_table; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE review_table (
    id integer NOT NULL,
    comment character varying,
    rating integer,
    created_at character varying,
    game_id integer,
    title character varying
);


ALTER TABLE review_table OWNER TO "Guest";

--
-- Name: review_table_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE review_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_table_id_seq OWNER TO "Guest";

--
-- Name: review_table_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE review_table_id_seq OWNED BY review_table.id;


--
-- Name: system_game_link; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE system_game_link (
    id integer NOT NULL,
    system_id integer,
    game_id integer
);


ALTER TABLE system_game_link OWNER TO "Guest";

--
-- Name: system_game_link_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE system_game_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE system_game_link_id_seq OWNER TO "Guest";

--
-- Name: system_game_link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE system_game_link_id_seq OWNED BY system_game_link.id;


--
-- Name: system_table; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE system_table (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE system_table OWNER TO "Guest";

--
-- Name: system_table_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE system_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE system_table_id_seq OWNER TO "Guest";

--
-- Name: system_table_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE system_table_id_seq OWNED BY system_table.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY game_table ALTER COLUMN id SET DEFAULT nextval('game_table_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY genre_game_link ALTER COLUMN id SET DEFAULT nextval('genre_game_link_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY genre_table ALTER COLUMN id SET DEFAULT nextval('genre_table_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY review_table ALTER COLUMN id SET DEFAULT nextval('review_table_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY system_game_link ALTER COLUMN id SET DEFAULT nextval('system_game_link_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY system_table ALTER COLUMN id SET DEFAULT nextval('system_table_id_seq'::regclass);


--
-- Data for Name: game_table; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY game_table (id, title, summary) FROM stdin;
48	Dark SOuls1	\N
49	dark souls2	\N
50		\N
51	MW3	\N
52		\N
53	GTA5	\N
54	WOW	The tits
55	Whoop Ass Game!	A very good game
\.


--
-- Name: game_table_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('game_table_id_seq', 55, true);


--
-- Data for Name: genre_game_link; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY genre_game_link (id, genre_id, game_id) FROM stdin;
14	10	48
15	11	48
16	12	48
17	11	49
18	12	49
19	10	50
20	10	51
21	12	51
22	10	53
23	10	54
24	12	55
\.


--
-- Name: genre_game_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('genre_game_link_id_seq', 24, true);


--
-- Data for Name: genre_table; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY genre_table (id, name) FROM stdin;
10	FPS
11	Platformer
12	Strategy
\.


--
-- Name: genre_table_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('genre_table_id_seq', 12, true);


--
-- Data for Name: review_table; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY review_table (id, comment, rating, created_at, game_id, title) FROM stdin;
2	okay	0	04/20/1969	3	\N
3	okay ma ass. Greatest game ever dude!	0	04/20/1969	3	\N
4		0	04/20/1969	1	\N
8	\N	5	04/20/1969	49	New review
10	\N	5	04/20/1969	48	
11	\N	5	04/20/1969	48	r
14	\N	5	04/20/1969	55	sadfsdf
15	\N	5	04/20/1969	53	test
16	\N	5	04/20/1969	51	
17	\N	5	04/20/1969	51	fdg
18	\N	5	04/20/1969	54	TEST
19	\N	5	04/20/1969	48	TEST
20	\N	5	04/20/1969	49	the test
21	did we do\r\n	5	04/20/1969	55	what
22	Spark	5	04/20/1969	55	Stupid
\.


--
-- Name: review_table_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('review_table_id_seq', 22, true);


--
-- Data for Name: system_game_link; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY system_game_link (id, system_id, game_id) FROM stdin;
16	32	48
17	33	48
18	34	48
19	32	49
20	32	50
21	32	51
22	33	51
23	34	51
24	32	53
25	33	53
26	34	53
27	32	54
28	32	55
29	33	55
30	34	55
\.


--
-- Name: system_game_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('system_game_link_id_seq', 30, true);


--
-- Data for Name: system_table; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY system_table (id, name) FROM stdin;
32	Xbox One
33	PS4
34	PC
\.


--
-- Name: system_table_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('system_table_id_seq', 34, true);


--
-- Name: game_table_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY game_table
    ADD CONSTRAINT game_table_pkey PRIMARY KEY (id);


--
-- Name: genre_game_link_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY genre_game_link
    ADD CONSTRAINT genre_game_link_pkey PRIMARY KEY (id);


--
-- Name: genre_table_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY genre_table
    ADD CONSTRAINT genre_table_pkey PRIMARY KEY (id);


--
-- Name: review_table_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY review_table
    ADD CONSTRAINT review_table_pkey PRIMARY KEY (id);


--
-- Name: system_game_link_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY system_game_link
    ADD CONSTRAINT system_game_link_pkey PRIMARY KEY (id);


--
-- Name: system_table_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY system_table
    ADD CONSTRAINT system_table_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

