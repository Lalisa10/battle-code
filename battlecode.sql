--
-- PostgreSQL database dump
--

\restrict DhcHQuCO2LbOxXhI0M9wGrmOjP01YxXBBPFtqUkQ6cSkgoygvQITbaC6kILCiHP

-- Dumped from database version 16.11 (Ubuntu 16.11-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.11 (Ubuntu 16.11-0ubuntu0.24.04.1)

-- Started on 2026-01-05 11:28:54 +07

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
-- TOC entry 229 (class 1259 OID 25315)
-- Name: match_participants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.match_participants (
    match_id bigint NOT NULL,
    submission_id bigint NOT NULL,
    slot smallint NOT NULL
);


ALTER TABLE public.match_participants OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 25289)
-- Name: matches; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matches (
    id bigint NOT NULL,
    problem_id bigint NOT NULL,
    tournament_id bigint,
    status character varying(20) NOT NULL,
    winner_submission_id bigint,
    events_url character varying(255),
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.matches OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 25288)
-- Name: matches_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matches_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.matches_id_seq OWNER TO postgres;

--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 227
-- Name: matches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matches_id_seq OWNED BY public.matches.id;


--
-- TOC entry 221 (class 1259 OID 25218)
-- Name: problems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.problems (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    engine_type character varying(255) NOT NULL,
    time_limit_ms integer NOT NULL,
    memory_limit_mb integer NOT NULL,
    is_active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.problems OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25217)
-- Name: problems_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.problems_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.problems_id_seq OWNER TO postgres;

--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 220
-- Name: problems_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.problems_id_seq OWNED BY public.problems.id;


--
-- TOC entry 218 (class 1259 OID 25194)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id smallint NOT NULL,
    name character varying(32) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25193)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 217
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 223 (class 1259 OID 25231)
-- Name: submissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.submissions (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    problem_id bigint NOT NULL,
    language character varying(20) NOT NULL,
    code_url character varying(255) NOT NULL,
    is_active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.submissions OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25230)
-- Name: submissions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.submissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.submissions_id_seq OWNER TO postgres;

--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 222
-- Name: submissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.submissions_id_seq OWNED BY public.submissions.id;


--
-- TOC entry 226 (class 1259 OID 25272)
-- Name: tournament_participants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tournament_participants (
    tournament_id bigint NOT NULL,
    user_id bigint NOT NULL,
    status character varying(20) NOT NULL,
    rank integer
);


ALTER TABLE public.tournament_participants OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25254)
-- Name: tournaments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tournaments (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    organizer_id bigint NOT NULL,
    problem_id bigint NOT NULL,
    status character varying(10) NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tournaments OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25253)
-- Name: tournaments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tournaments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tournaments_id_seq OWNER TO postgres;

--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 224
-- Name: tournaments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tournaments_id_seq OWNED BY public.tournaments.id;


--
-- TOC entry 219 (class 1259 OID 25202)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id smallint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 25179)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    handle character varying(50) NOT NULL,
    email character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    is_active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25178)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3332 (class 2604 OID 25292)
-- Name: matches id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matches ALTER COLUMN id SET DEFAULT nextval('public.matches_id_seq'::regclass);


--
-- TOC entry 3324 (class 2604 OID 25221)
-- Name: problems id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.problems ALTER COLUMN id SET DEFAULT nextval('public.problems_id_seq'::regclass);


--
-- TOC entry 3323 (class 2604 OID 25197)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 3327 (class 2604 OID 25234)
-- Name: submissions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.submissions ALTER COLUMN id SET DEFAULT nextval('public.submissions_id_seq'::regclass);


--
-- TOC entry 3330 (class 2604 OID 25257)
-- Name: tournaments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournaments ALTER COLUMN id SET DEFAULT nextval('public.tournaments_id_seq'::regclass);


--
-- TOC entry 3320 (class 2604 OID 25182)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3539 (class 0 OID 25315)
-- Dependencies: 229
-- Data for Name: match_participants; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.match_participants (match_id, submission_id, slot) FROM stdin;
1	1	2
\.


--
-- TOC entry 3538 (class 0 OID 25289)
-- Dependencies: 228
-- Data for Name: matches; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matches (id, problem_id, tournament_id, status, winner_submission_id, events_url, created_at) FROM stdin;
1	1	2	PENDING	\N	\N	2026-01-04 17:17:14.382996
\.


--
-- TOC entry 3531 (class 0 OID 25218)
-- Dependencies: 221
-- Data for Name: problems; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.problems (id, code, name, description, engine_type, time_limit_ms, memory_limit_mb, is_active, created_at) FROM stdin;
1	tic-tac-toe-5	Cờ caro 5 quân	Cho một bảng ô vuông m x n, người chơi lần lượt điền ký hiệu, ai tạo được 5 quân liên tiếp theo hàng, cột hoặc đường chéo trước sẽ thắng.	tic_tac_toe	2000	128	t	2026-01-02 23:22:37.511374
\.


--
-- TOC entry 3528 (class 0 OID 25194)
-- Dependencies: 218
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	PLAYER
2	ORGANIZER
3	ADMIN
\.


--
-- TOC entry 3533 (class 0 OID 25231)
-- Dependencies: 223
-- Data for Name: submissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.submissions (id, user_id, problem_id, language, code_url, is_active, created_at) FROM stdin;
1	5	1	cpp	uploads/submissions/2026/1/91ec5db0-a0a8-4bf9-9620-206b77b6aa93.cpp	f	2026-01-03 06:58:49.540232
\.


--
-- TOC entry 3536 (class 0 OID 25272)
-- Dependencies: 226
-- Data for Name: tournament_participants; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tournament_participants (tournament_id, user_id, status, rank) FROM stdin;
\.


--
-- TOC entry 3535 (class 0 OID 25254)
-- Dependencies: 225
-- Data for Name: tournaments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tournaments (id, name, organizer_id, problem_id, status, created_at) FROM stdin;
1	Giải đấu cờ caro	5	1	UPCOMING	2026-01-04 16:59:58.246706
2	Giải đấu cờ caro	5	1	UPCOMING	2026-01-04 17:01:44.479719
\.


--
-- TOC entry 3529 (class 0 OID 25202)
-- Dependencies: 219
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (user_id, role_id) FROM stdin;
5	1
\.


--
-- TOC entry 3526 (class 0 OID 25179)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, handle, email, password_hash, is_active, created_at) FROM stdin;
5	Ire	truc12a2cvp@gmail.com	$2a$10$IeS2jThrgtOFd2kc.x6GmO8OfWT3MANqgT0piEPFazyvOtkXe3y..	f	2026-01-02 14:28:00.775423
\.


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 227
-- Name: matches_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matches_id_seq', 1, true);


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 220
-- Name: problems_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.problems_id_seq', 3, true);


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 217
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 59, true);


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 222
-- Name: submissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.submissions_id_seq', 1, true);


--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 224
-- Name: tournaments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tournaments_id_seq', 2, true);


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 5, true);


--
-- TOC entry 3366 (class 2606 OID 25321)
-- Name: match_participants match_participants_match_id_slot_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match_participants
    ADD CONSTRAINT match_participants_match_id_slot_key UNIQUE (match_id, slot);


--
-- TOC entry 3368 (class 2606 OID 25319)
-- Name: match_participants match_participants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match_participants
    ADD CONSTRAINT match_participants_pkey PRIMARY KEY (match_id, submission_id);


--
-- TOC entry 3363 (class 2606 OID 25297)
-- Name: matches matches_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matches
    ADD CONSTRAINT matches_pkey PRIMARY KEY (id);


--
-- TOC entry 3347 (class 2606 OID 25342)
-- Name: problems problems_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.problems
    ADD CONSTRAINT problems_code_key UNIQUE (code);


--
-- TOC entry 3349 (class 2606 OID 25227)
-- Name: problems problems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.problems
    ADD CONSTRAINT problems_pkey PRIMARY KEY (id);


--
-- TOC entry 3341 (class 2606 OID 25201)
-- Name: roles roles_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);


--
-- TOC entry 3343 (class 2606 OID 25199)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3353 (class 2606 OID 25240)
-- Name: submissions submissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.submissions
    ADD CONSTRAINT submissions_pkey PRIMARY KEY (id);


--
-- TOC entry 3359 (class 2606 OID 25276)
-- Name: tournament_participants tournament_participants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament_participants
    ADD CONSTRAINT tournament_participants_pkey PRIMARY KEY (tournament_id, user_id);


--
-- TOC entry 3356 (class 2606 OID 25260)
-- Name: tournaments tournaments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournaments
    ADD CONSTRAINT tournaments_pkey PRIMARY KEY (id);


--
-- TOC entry 3345 (class 2606 OID 25206)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 3335 (class 2606 OID 25192)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3337 (class 2606 OID 25190)
-- Name: users users_handle_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_handle_key UNIQUE (handle);


--
-- TOC entry 3339 (class 2606 OID 25188)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3364 (class 1259 OID 25332)
-- Name: idx_match_participants_submission; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_match_participants_submission ON public.match_participants USING btree (submission_id);


--
-- TOC entry 3360 (class 1259 OID 25313)
-- Name: idx_matches_problem; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_matches_problem ON public.matches USING btree (problem_id);


--
-- TOC entry 3361 (class 1259 OID 25314)
-- Name: idx_matches_tournament; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_matches_tournament ON public.matches USING btree (tournament_id);


--
-- TOC entry 3350 (class 1259 OID 25252)
-- Name: idx_submissions_problem; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_submissions_problem ON public.submissions USING btree (problem_id);


--
-- TOC entry 3351 (class 1259 OID 25251)
-- Name: idx_submissions_user; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_submissions_user ON public.submissions USING btree (user_id);


--
-- TOC entry 3357 (class 1259 OID 25287)
-- Name: idx_tour_participants_user; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_tour_participants_user ON public.tournament_participants USING btree (user_id);


--
-- TOC entry 3354 (class 1259 OID 25271)
-- Name: idx_tournaments_problem; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_tournaments_problem ON public.tournaments USING btree (problem_id);


--
-- TOC entry 3380 (class 2606 OID 25322)
-- Name: match_participants match_participants_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match_participants
    ADD CONSTRAINT match_participants_match_id_fkey FOREIGN KEY (match_id) REFERENCES public.matches(id) ON DELETE CASCADE;


--
-- TOC entry 3381 (class 2606 OID 25327)
-- Name: match_participants match_participants_submission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match_participants
    ADD CONSTRAINT match_participants_submission_id_fkey FOREIGN KEY (submission_id) REFERENCES public.submissions(id) ON DELETE CASCADE;


--
-- TOC entry 3377 (class 2606 OID 25298)
-- Name: matches matches_problem_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matches
    ADD CONSTRAINT matches_problem_id_fkey FOREIGN KEY (problem_id) REFERENCES public.problems(id);


--
-- TOC entry 3378 (class 2606 OID 25303)
-- Name: matches matches_tournament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matches
    ADD CONSTRAINT matches_tournament_id_fkey FOREIGN KEY (tournament_id) REFERENCES public.tournaments(id) ON DELETE SET NULL;


--
-- TOC entry 3379 (class 2606 OID 25308)
-- Name: matches matches_winner_submission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matches
    ADD CONSTRAINT matches_winner_submission_id_fkey FOREIGN KEY (winner_submission_id) REFERENCES public.submissions(id);


--
-- TOC entry 3371 (class 2606 OID 25246)
-- Name: submissions submissions_problem_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.submissions
    ADD CONSTRAINT submissions_problem_id_fkey FOREIGN KEY (problem_id) REFERENCES public.problems(id) ON DELETE CASCADE;


--
-- TOC entry 3372 (class 2606 OID 25241)
-- Name: submissions submissions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.submissions
    ADD CONSTRAINT submissions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3375 (class 2606 OID 25277)
-- Name: tournament_participants tournament_participants_tournament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament_participants
    ADD CONSTRAINT tournament_participants_tournament_id_fkey FOREIGN KEY (tournament_id) REFERENCES public.tournaments(id) ON DELETE CASCADE;


--
-- TOC entry 3376 (class 2606 OID 25282)
-- Name: tournament_participants tournament_participants_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament_participants
    ADD CONSTRAINT tournament_participants_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3373 (class 2606 OID 25261)
-- Name: tournaments tournaments_organizer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournaments
    ADD CONSTRAINT tournaments_organizer_id_fkey FOREIGN KEY (organizer_id) REFERENCES public.users(id);


--
-- TOC entry 3374 (class 2606 OID 25266)
-- Name: tournaments tournaments_problem_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournaments
    ADD CONSTRAINT tournaments_problem_id_fkey FOREIGN KEY (problem_id) REFERENCES public.problems(id);


--
-- TOC entry 3369 (class 2606 OID 25212)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id) ON DELETE CASCADE;


--
-- TOC entry 3370 (class 2606 OID 25207)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2026-01-05 11:28:54 +07

--
-- PostgreSQL database dump complete
--

\unrestrict DhcHQuCO2LbOxXhI0M9wGrmOjP01YxXBBPFtqUkQ6cSkgoygvQITbaC6kILCiHP

