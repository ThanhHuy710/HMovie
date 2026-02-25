--
-- PostgreSQL database dump
--

\restrict JNgdvDuLcd9TkFbOs5saL2KKvZwZvxCpv1IqdZ6dhnE0yf6stoFetEV6JbsseNV

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-02-25 03:06:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 220 (class 1259 OID 16424)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    cart_id character varying(255) NOT NULL,
    added_at timestamp(6) without time zone,
    plan_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16432)
-- Name: episode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.episode (
    episode_id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    episode_name character varying(255),
    suburl character varying(255),
    updated_at timestamp(6) without time zone,
    videourl character varying(255),
    movie_id character varying(255)
);


ALTER TABLE public.episode OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16440)
-- Name: favorite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorite (
    favorite_id character varying(255) NOT NULL,
    added_at timestamp(6) without time zone,
    movie_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE public.favorite OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16448)
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
    feedback_id character varying(255) NOT NULL,
    comment text,
    created_at timestamp(6) without time zone,
    rating integer,
    updated_at timestamp(6) without time zone,
    movie_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16576)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    genre_id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    name character varying(255),
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16472)
-- Name: invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice (
    invoice_id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    end_date date,
    payment_method character varying(255),
    start_date date,
    status character varying(255),
    total_price numeric(38,2),
    plan_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE public.invoice OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16480)
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie (
    movie_id character varying(255) NOT NULL,
    actor text,
    age_rating character varying(255),
    average_rating numeric(38,2),
    country character varying(255),
    created_at timestamp(6) without time zone,
    description text,
    director character varying(255),
    duration character varying(255),
    is_series boolean,
    original_name character varying(255),
    posterurl character varying(255),
    poster_videourl character varying(255),
    season integer,
    title character varying(255),
    updated_at timestamp(6) without time zone,
    view_count bigint,
    year integer,
    favorite_count bigint
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16584)
-- Name: movie_genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie_genre (
    movie_genre_id character varying(255) NOT NULL,
    genre_id character varying(255),
    movie_id character varying(255)
);


ALTER TABLE public.movie_genre OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16488)
-- Name: plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan (
    plan_id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    duration_days integer,
    name character varying(255),
    price numeric(38,2),
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.plan OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16414)
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    profile_id character varying(255) NOT NULL,
    dob date,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(255),
    user_id character varying(255),
    username character varying(255),
    create_at timestamp(6) without time zone,
    gender boolean,
    interest character varying(255),
    update_at timestamp(6) without time zone,
    avatar character varying(255),
    plan_id character varying(255),
    expert_time_plan timestamp(6) without time zone
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16496)
-- Name: view; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.view (
    view_id character varying(255) NOT NULL,
    progress integer,
    viewed_at timestamp(6) without time zone,
    episode_id character varying(255),
    movie_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE public.view OWNER TO postgres;

--
-- TOC entry 5084 (class 0 OID 16424)
-- Dependencies: 220
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (cart_id, added_at, plan_id, profile_id) FROM stdin;
\.


--
-- TOC entry 5085 (class 0 OID 16432)
-- Dependencies: 221
-- Data for Name: episode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.episode (episode_id, created_at, episode_name, suburl, updated_at, videourl, movie_id) FROM stdin;
1	2025-11-29 16:34:27	Tập 01	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/EmVkj4ja/index.m3u8	1
2	2025-11-29 16:34:27	Tập 02	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/EvnfMbra/index.m3u8	1
3	2025-11-29 16:34:27	Tập 03	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/M3rCxLhi/index.m3u8	1
4	2025-11-29 16:34:27	Tập 04	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/zrUAmZzh/index.m3u8	1
5	2025-11-29 16:34:27	Tập 05	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/a65hoglT/index.m3u8	1
6	2025-11-29 16:34:27	Tập 06	\N	2025-11-29 16:34:27	https://s6.kkphimplayer6.com/20251129/FiQpRKNr/index.m3u8	1
7	2025-11-29 17:12:52	Tập 01 (Vietsub)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251104/AO4rFeD8/index.m3u8	2
8	2025-11-29 17:12:52	Tập 02 (Vietsub)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251104/72b4ZHds/index.m3u8	2
9	2025-11-29 17:12:52	Tập 03 (Vietsub)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251109/0OIwvhtP/index.m3u8	2
10	2025-11-29 17:12:52	Tập 04 (Vietsub)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251117/yAsSwdDg/index.m3u8	2
11	2025-11-29 17:12:52	Tập 05 (Vietsub)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251123/P0FZaBlD/index.m3u8	2
12	2025-11-29 17:12:52	Tập 01 (Thuyết Minh)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251129/dK5gtwf0/index.m3u8	2
13	2025-11-29 17:12:52	Tập 02 (Thuyết Minh)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251129/vXBedCNy/index.m3u8	2
14	2025-11-29 17:12:52	Tập 03 (Thuyết Minh)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251129/BEeouyoM/index.m3u8	2
15	2025-11-29 17:12:52	Tập 04 (Thuyết Minh)	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251129/DdbIqOqn/index.m3u8	2
16	2025-11-29 17:12:52	Tập 01	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251011/B0Th1h7T/index.m3u8	3
17	2025-11-29 17:12:52	Tập 02	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251011/th0v3PWP/index.m3u8	3
18	2025-11-29 17:12:52	Tập 03	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251018/WGRA6r8W/index.m3u8	3
19	2025-11-29 17:12:52	Tập 04	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251029/aVa4QaXR/index.m3u8	3
20	2025-11-29 17:12:52	Tập 05	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251101/XsH7EEo7/index.m3u8	3
21	2025-11-29 17:12:52	Tập 06	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251111/fOnKQuQZ/index.m3u8	3
22	2025-11-29 17:12:52	Tập 07	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251115/ph7KIQfe/index.m3u8	3
23	2025-11-29 17:12:52	Tập 08	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251123/MWqhCv8h/index.m3u8	3
24	2025-11-29 17:12:52	Tập 09	\N	2025-11-29 17:12:52	https://s6.kkphimplayer6.com/20251129/QPuUAMdV/index.m3u8	3
25	2025-11-29 17:20:21	Tập 01	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251005/DLHyafVi/index.m3u8	4
26	2025-11-29 17:20:21	Tập 02	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251011/gzl4FX8S/index.m3u8	4
27	2025-11-29 17:20:21	Tập 03	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251021/ksfZH7TW/index.m3u8	4
28	2025-11-29 17:20:21	Tập 04	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251026/ITZFR11v/index.m3u8	4
29	2025-11-29 17:20:21	Tập 05	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251103/vLneaUSM/index.m3u8	4
30	2025-11-29 17:20:21	Tập 06	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251109/c3G736Dx/index.m3u8	4
31	2025-11-29 17:20:21	Tập 07	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251115/MrRktsC7/index.m3u8	4
32	2025-11-29 17:20:21	Tập 08	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251122/7YYtuGMC/index.m3u8	4
33	2025-11-29 17:20:21	Tập 09	\N	2025-11-29 17:20:21	https://s6.kkphimplayer6.com/20251129/5Q98ctaK/index.m3u8	4
34	2025-11-29 17:26:31	Full	\N	2025-11-29 17:26:31	https://s6.kkphimplayer6.com/20251129/tNo4KDOx/index.m3u8	5
35	2025-11-29 17:29:04	Tập 01	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20250930/QQ5wBeqF/index.m3u8	6
36	2025-11-29 17:29:04	Tập 02	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20250930/qhFg13zR/index.m3u8	6
37	2025-11-29 17:29:04	Tập 03	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251005/aL4tBMYG/index.m3u8	6
38	2025-11-29 17:29:04	Tập 04	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251011/uP1luLjr/index.m3u8	6
39	2025-11-29 17:29:04	Tập 05	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251018/GdZa9ToA/index.m3u8	6
40	2025-11-29 17:29:04	Tập 06	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251025/gC833ehE/index.m3u8	6
41	2025-11-29 17:29:04	Tập 07	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251101/Z6MPINXK/index.m3u8	6
42	2025-11-29 17:29:04	Tập 08	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251109/pqgPiLYB/index.m3u8	6
43	2025-11-29 17:29:04	Tập 09	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251115/9KShxia3/index.m3u8	6
44	2025-11-29 17:29:04	Tập 10	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251122/7i30t2gc/index.m3u8	6
45	2025-11-29 17:29:04	Tập 11	\N	2025-11-29 17:29:04	https://s6.kkphimplayer6.com/20251129/ZIN6JB1x/index.m3u8	6
46	2025-11-29 17:37:48	Tập 01	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/smT8CzsO/index.m3u8	7
47	2025-11-29 17:37:48	Tập 02	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/HMEs2zcw/index.m3u8	7
48	2025-11-29 17:37:48	Tập 03	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/RFqkVOq9/index.m3u8	7
49	2025-11-29 17:37:48	Tập 04	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/UfIT9sRy/index.m3u8	7
50	2025-11-29 17:37:48	Tập 05	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/6FjN42uG/index.m3u8	7
51	2025-11-29 17:37:48	Tập 06	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/4YCLhR2z/index.m3u8	7
52	2025-11-29 17:37:48	Tập 07	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/1gjtBYPy/index.m3u8	7
53	2025-11-29 17:37:48	Tập 08	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/BdGKyGlE/index.m3u8	7
54	2025-11-29 17:37:48	Tập 09	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/9TcIEovS/index.m3u8	7
55	2025-11-29 17:37:48	Tập 10	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/DOysub59/index.m3u8	7
56	2025-11-29 17:37:48	Tập 11	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/4nTW9GRm/index.m3u8	7
57	2025-11-29 17:37:48	Tập 12	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/iuLXsXpc/index.m3u8	7
58	2025-11-29 17:37:48	Tập 13	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/2CBkbClo/index.m3u8	7
59	2025-11-29 17:37:48	Tập 14	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/bynEkXm1/index.m3u8	7
60	2025-11-29 17:37:48	Tập 15	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/AC1Kzyxk/index.m3u8	7
61	2025-11-29 17:37:48	Tập 16	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/5Xhyio39/index.m3u8	7
62	2025-11-29 17:37:48	Tập 17	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/eSCEon6j/index.m3u8	7
63	2025-11-29 17:37:48	Tập 18	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/5E79atkT/index.m3u8	7
64	2025-11-29 17:37:48	Tập 19	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/3yMNg8dN/index.m3u8	7
65	2025-11-29 17:37:48	Tập 20	\N	2025-11-29 17:37:48	https://s4.phim1280.tv/20250219/9BLLzbWU/index.m3u8	7
66	2025-11-29 17:44:22	Full	\N	2025-11-29 17:44:22	https://s6.kkphimplayer6.com/20251129/6h7yalWV/index.m3u8	8
67	2025-11-29 17:46:24	Full	\N	2025-11-29 17:46:24	https://s6.kkphimplayer6.com/20251129/DFnrczxX/index.m3u8	9
68	2025-11-29 17:47:27	Full	\N	2025-11-29 17:47:27	https://s6.kkphimplayer6.com/20251129/TUaynpW1/index.m3u8	10
69	2025-11-29 17:49:21	Tập 01	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251023/RdN812j1/index.m3u8	11
70	2025-11-29 17:49:21	Tập 02	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251031/xpz3g42Z/index.m3u8	11
71	2025-11-29 17:49:21	Tập 03	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251112/YG8ogFGG/index.m3u8	11
72	2025-11-29 17:49:21	Tập 04	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251112/67bnnua3/index.m3u8	11
73	2025-11-29 17:49:21	Tập 05	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251121/zQp42Oul/index.m3u8	11
74	2025-11-29 17:49:21	Tập 06	\N	2025-11-29 17:49:21	https://s6.kkphimplayer6.com/20251129/INNnXszZ/index.m3u8	11
75	2025-11-29 17:50:39	Tập 01 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/BgquPVMY/index.m3u8	12
76	2025-11-29 17:50:39	Tập 02 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/k1f4HNE2/index.m3u8	12
77	2025-11-29 17:50:39	Tập 03 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/OCaFYS4W/index.m3u8	12
78	2025-11-29 17:50:39	Tập 04 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/xbB9sRow/index.m3u8	12
79	2025-11-29 17:50:39	Tập 05 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/ewDUcfo9/index.m3u8	12
80	2025-11-29 17:50:39	Tập 06 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/IZO2u7jd/index.m3u8	12
81	2025-11-29 17:50:39	Tập 07 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/Cy0AlpVy/index.m3u8	12
82	2025-11-29 17:50:39	Tập 08 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250924/ehfUOM48/index.m3u8	12
83	2025-11-29 17:50:39	Tập 09 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250929/uWUhlxry/index.m3u8	12
84	2025-11-29 17:50:39	Tập 10 (Vietsub)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20251002/CiJpFnxw/index.m3u8	12
85	2025-11-29 17:50:39	Tập 01 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/ediWVa0r/index.m3u8	12
86	2025-11-29 17:50:39	Tập 02 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/hWuTXItx/index.m3u8	12
87	2025-11-29 17:50:39	Tập 03 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/wkCqrZzs/index.m3u8	12
88	2025-11-29 17:50:39	Tập 04 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/JTkARXCT/index.m3u8	12
89	2025-11-29 17:50:39	Tập 05 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/6rrO8LqY/index.m3u8	12
90	2025-11-29 17:50:39	Tập 06 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/5RZ76XlT/index.m3u8	12
91	2025-11-29 17:50:39	Tập 07 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250920/1IdCW140/index.m3u8	12
92	2025-11-29 17:50:39	Tập 08 (Thuyết Minh)	\N	2025-11-29 17:50:39	https://s6.kkphimplayer6.com/20250924/gg44PUxo/index.m3u8	12
93	2025-11-29 17:51:32	Tập 01 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/vNawoxyo/index.m3u8	13
94	2025-11-29 17:51:32	Tập 02 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/WO6QXBVN/index.m3u8	13
95	2025-11-29 17:51:32	Tập 03 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/7CmqMhJv/index.m3u8	13
96	2025-11-29 17:51:32	Tập 04 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/Rqcv1Dj0/index.m3u8	13
97	2025-11-29 17:51:32	Tập 05 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/PKTTcpgP/index.m3u8	13
98	2025-11-29 17:51:32	Tập 06 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250824/0QgAqkoG/index.m3u8	13
99	2025-11-29 17:51:32	Tập 07 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250829/cf3cJ5XQ/index.m3u8	13
100	2025-11-29 17:51:32	Tập 08 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250830/VBRLsBCK/index.m3u8	13
101	2025-11-29 17:51:32	Tập 09 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250905/hqle3dvf/index.m3u8	13
102	2025-11-29 17:51:32	Tập 10 (Vietsub)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250906/4OeSnELU/index.m3u8	13
103	2025-11-29 17:51:32	Tập 01 (Thuyết Minh)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/2GE7R44H/index.m3u8	13
104	2025-11-29 17:51:32	Tập 02 (Thuyết Minh)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/rQvRmYn7/index.m3u8	13
105	2025-11-29 17:51:32	Tập 03 (Thuyết Minh)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/x9WwLsp2/index.m3u8	13
106	2025-11-29 17:51:32	Tập 04 (Thuyết Minh)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/haeqIQ5z/index.m3u8	13
107	2025-11-29 17:51:32	Tập 05 (Thuyết Minh)	\N	2025-11-29 17:51:32	https://s6.kkphimplayer6.com/20250823/vavL97dk/index.m3u8	13
108	2025-11-29 17:51:49	Tập 01 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/vNawoxyo/index.m3u8	13
109	2025-11-29 17:51:49	Tập 02 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/WO6QXBVN/index.m3u8	13
110	2025-11-29 17:51:49	Tập 03 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/7CmqMhJv/index.m3u8	13
111	2025-11-29 17:51:49	Tập 04 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/Rqcv1Dj0/index.m3u8	13
112	2025-11-29 17:51:49	Tập 05 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/PKTTcpgP/index.m3u8	13
113	2025-11-29 17:51:49	Tập 06 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250824/0QgAqkoG/index.m3u8	13
114	2025-11-29 17:51:49	Tập 07 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250829/cf3cJ5XQ/index.m3u8	13
115	2025-11-29 17:51:49	Tập 08 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250830/VBRLsBCK/index.m3u8	13
116	2025-11-29 17:51:49	Tập 09 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250905/hqle3dvf/index.m3u8	13
117	2025-11-29 17:51:49	Tập 10 (Vietsub)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250906/4OeSnELU/index.m3u8	13
118	2025-11-29 17:51:49	Tập 01 (Thuyết Minh)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/2GE7R44H/index.m3u8	13
119	2025-11-29 17:51:49	Tập 02 (Thuyết Minh)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/rQvRmYn7/index.m3u8	13
120	2025-11-29 17:51:49	Tập 03 (Thuyết Minh)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/x9WwLsp2/index.m3u8	13
121	2025-11-29 17:51:49	Tập 04 (Thuyết Minh)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/haeqIQ5z/index.m3u8	13
122	2025-11-29 17:51:49	Tập 05 (Thuyết Minh)	\N	2025-11-29 17:51:49	https://s6.kkphimplayer6.com/20250823/vavL97dk/index.m3u8	13
123	2025-11-29 17:53:29	Tập 01 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/mEQy8gd6/index.m3u8	14
124	2025-11-29 17:53:29	Tập 02 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/SQsM8IoI/index.m3u8	14
125	2025-11-29 17:53:29	Tập 03 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/s14EqI9m/index.m3u8	14
126	2025-11-29 17:53:29	Tập 04 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/9mARnRxI/index.m3u8	14
127	2025-11-29 17:53:29	Tập 05 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/zC2XRYE5/index.m3u8	14
128	2025-11-29 17:53:29	Tập 06 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/aXX4wzgh/index.m3u8	14
129	2025-11-29 17:53:29	Tập 07 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/tFOuKl5R/index.m3u8	14
130	2025-11-29 17:53:29	Tập 08 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/vvZr0Ihr/index.m3u8	14
131	2025-11-29 17:53:29	Tập 09 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250719/n09thOpB/index.m3u8	14
132	2025-11-29 17:53:29	Tập 10 (Vietsub)	\N	2025-11-29 17:53:29	https://s6.kkphimplayer6.com/20250723/HewbjSyJ/index.m3u8	14
133	2025-11-29 18:02:51	Tập 01-03 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/jss6azUX/index.m3u8	15
134	2025-11-29 18:02:51	Tập 04-05 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/V6HkY3je/index.m3u8	15
135	2025-11-29 18:02:51	Tập 06 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/9sgeaRE7/index.m3u8	15
136	2025-11-29 18:02:51	Tập 07 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/JtmUKtMg/index.m3u8	15
137	2025-11-29 18:02:51	Tập 08 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/ppXcr5ZP/index.m3u8	15
138	2025-11-29 18:02:51	Tập 09 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/N03rabBu/index.m3u8	15
139	2025-11-29 18:02:51	Tập 10 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/TTTCcJPl/index.m3u8	15
140	2025-11-29 18:02:51	Tập 11 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/SWHtCKos/index.m3u8	15
141	2025-11-29 18:02:51	Tập 12 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/vyK0HJ7f/index.m3u8	15
142	2025-11-29 18:02:51	Tập 13 (Vietsub)	\N	2025-11-29 18:02:51	https://s5.phim1280.tv/20250308/vqZGzoKD/index.m3u8	15
143	2025-11-29 18:04:33	Full	\N	2025-11-29 18:04:33	https://s6.kkphimplayer6.com/20251129/ekz2M85a/index.m3u8	17
144	2025-11-29 18:06:04	Full	\N	2025-11-29 18:06:04	https://s6.kkphimplayer6.com/20251129/iPta4xoJ/index.m3u8	19
145	2025-11-29 18:07:31	Full	\N	2025-11-29 18:07:31	https://s6.kkphimplayer6.com/20251129/CjXfkgk6/index.m3u8	20
146	2025-11-29 18:08:46	Full	\N	2025-11-29 18:08:46	https://s6.kkphimplayer6.com/20251129/CjXfkgk6/index.m3u8	20
147	2025-11-29 18:09:17	Full	\N	2025-11-29 18:09:17	https://s6.kkphimplayer6.com/20251129/wnvkCMIF/index.m3u8	21
148	2025-11-29 18:09:59	Full	\N	2025-11-29 18:09:59	https://s6.kkphimplayer6.com/20251129/GFng6VOY/index.m3u8	22
149	2025-11-29 18:10:40	Full	\N	2025-11-29 18:10:40	https://s6.kkphimplayer6.com/20251129/e1PKNzOC/index.m3u8	23
150	2025-11-29 18:12:43	Tập 01	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251122/sbKILOgs/index.m3u8	24
151	2025-11-29 18:12:43	Tập 02	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251122/9MCPS0Wg/index.m3u8	24
152	2025-11-29 18:12:43	Tập 03	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251122/1xgUCd5P/index.m3u8	24
153	2025-11-29 18:12:43	Tập 04	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251122/RkqrUhyn/index.m3u8	24
154	2025-11-29 18:12:43	Tập 05	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251125/TEq0r8yE/index.m3u8	24
155	2025-11-29 18:12:43	Tập 06	\N	2025-11-29 18:12:43	https://s6.kkphimplayer6.com/20251129/h5VDoknK/index.m3u8	24
156	2025-11-29 18:15:18	Full (Vietsub)	\N	2025-11-29 18:15:18	https://s6.kkphimplayer6.com/20251129/6gLUUGrF/index.m3u8	26
157	2025-11-29 18:15:18	Full (Lồng Tiếng)	\N	2025-11-29 18:15:18	https://s6.kkphimplayer6.com/20251129/ym4PDR7q/index.m3u8	26
158	2025-11-29 18:16:03	Tập 01 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250714/ZA8EPysx/index.m3u8	27
159	2025-11-29 18:16:03	Tập 02 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250720/VIc1P0Ob/index.m3u8	27
160	2025-11-29 18:16:03	Tập 03 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250725/lJaXu5uw/index.m3u8	27
161	2025-11-29 18:16:03	Tập 04 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250809/2SOArnD9/index.m3u8	27
162	2025-11-29 18:16:03	Tập 05 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250809/GtxcaDWd/index.m3u8	27
163	2025-11-29 18:16:03	Tập 06 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250809/O1sWe13L/index.m3u8	27
164	2025-11-29 18:16:03	Tập 07 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250817/zcjUJ55f/index.m3u8	27
165	2025-11-29 18:16:03	Tập 08 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250913/1jaoIcxu/index.m3u8	27
166	2025-11-29 18:16:03	Tập 09 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250913/pnm58ruw/index.m3u8	27
167	2025-11-29 18:16:03	Tập 10 (Vietsub)	\N	2025-11-29 18:16:03	https://s6.kkphimplayer6.com/20250913/DAkURu0I/index.m3u8	27
168	2025-11-29 18:16:56	Tập 01 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251025/h4LbELlO/index.m3u8	28
169	2025-11-29 18:16:56	Tập 02 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251101/TFAibohx/index.m3u8	28
170	2025-11-29 18:16:56	Tập 03 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251109/Fghuoxwy/index.m3u8	28
171	2025-11-29 18:16:56	Tập 04 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251115/H9gbdYmA/index.m3u8	28
172	2025-11-29 18:16:56	Tập 05 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251122/671qv2NH/index.m3u8	28
173	2025-11-29 18:16:56	Tập 06 (Vietsub)	\N	2025-11-29 18:16:56	https://s6.kkphimplayer6.com/20251129/qTo9UDmX/index.m3u8	28
174	2025-11-29 18:17:49	Tập 01 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251024/bjsm1WRm/index.m3u8	29
175	2025-11-29 18:17:49	Tập 02 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251024/3GC3bSiM/index.m3u8	29
176	2025-11-29 18:17:49	Tập 03 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251107/TpPA5Bx4/index.m3u8	29
177	2025-11-29 18:17:49	Tập 04 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251107/vm5peMCe/index.m3u8	29
178	2025-11-29 18:17:49	Tập 05 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251113/TxfuSVKB/index.m3u8	29
179	2025-11-29 18:17:49	Tập 06 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251119/559emJOg/index.m3u8	29
180	2025-11-29 18:17:49	Tập 07 (Vietsub)	\N	2025-11-29 18:17:49	https://s6.kkphimplayer6.com/20251129/mywNtrTz/index.m3u8	29
181	2025-11-29 18:18:41	Tập 01 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251004/4tWnVyHD/index.m3u8	30
182	2025-11-29 18:18:41	Tập 02 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251011/yzr4TXqV/index.m3u8	30
183	2025-11-29 18:18:41	Tập 03 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251018/Ig3f8uih/index.m3u8	30
184	2025-11-29 18:18:41	Tập 04 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251025/Vb6jVmiH/index.m3u8	30
185	2025-11-29 18:18:41	Tập 05 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251101/7k5K1PQs/index.m3u8	30
186	2025-11-29 18:18:41	Tập 06 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251109/OBFlNXhc/index.m3u8	30
187	2025-11-29 18:18:41	Tập 07 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251115/fejwbsVW/index.m3u8	30
188	2025-11-29 18:18:41	Tập 08 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251122/1V9bhJzp/index.m3u8	30
189	2025-11-29 18:18:41	Tập 09 (Vietsub)	\N	2025-11-29 18:18:41	https://s6.kkphimplayer6.com/20251129/l3bIi6Gm/index.m3u8	30
190	2025-12-01 12:05:01	Tập 01	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/Did6WxuQ/index.m3u8	31
191	2025-12-01 12:05:01	Tập 02	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/bIDQAt09/index.m3u8	31
192	2025-12-01 12:05:01	Tập 03	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/pZ6qc7Rg/index.m3u8	31
193	2025-12-01 12:05:01	Tập 04	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/AAwkF0x4/index.m3u8	31
194	2025-12-01 12:05:01	Tập 05	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/iOMogwxx/index.m3u8	31
195	2025-12-01 12:05:01	Tập 06	\N	2025-12-01 12:05:01	https://s3.phim1280.tv/20240515/ittdo03k/index.m3u8	31
\.


--
-- TOC entry 5086 (class 0 OID 16440)
-- Dependencies: 222
-- Data for Name: favorite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favorite (favorite_id, added_at, movie_id, profile_id) FROM stdin;
ae89235f-e910-4156-9381-85631649a303	2026-02-22 22:57:00.788637	8	36bd9503-7a8a-4a00-acde-39d35c906b93
3084362d-2179-4da2-bbf0-19d868e4c7bd	2026-02-22 23:13:19.142718	7	36bd9503-7a8a-4a00-acde-39d35c906b93
7437c93a-4162-49ec-a88c-1adcc29d4f87	2026-02-22 23:13:30.997824	12	36bd9503-7a8a-4a00-acde-39d35c906b93
9619427c-2d50-4b37-8519-3887421db890	2026-02-24 20:13:33.979953	1	36bd9503-7a8a-4a00-acde-39d35c906b93
\.


--
-- TOC entry 5087 (class 0 OID 16448)
-- Dependencies: 223
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feedback (feedback_id, comment, created_at, rating, updated_at, movie_id, profile_id) FROM stdin;
8c571a0d-491b-446d-a0b0-23e03593a824	hayyy	2026-02-22 18:51:21.779208	10	2026-02-22 18:51:21.779208	30	36bd9503-7a8a-4a00-acde-39d35c906b93
541aea9d-1c0b-49dc-8884-08b96cd6ab01	\N	2026-02-22 18:51:39.09609	10	2026-02-22 18:51:39.09609	30	36bd9503-7a8a-4a00-acde-39d35c906b93
17edfe39-9eb1-41a6-a663-d298eb331561	dỡ	2026-02-22 19:49:56.229299	1	2026-02-22 19:49:56.237434	19	36bd9503-7a8a-4a00-acde-39d35c906b93
5c77d737-cec7-4bc7-8439-c5d21cfc4261	hayy	2026-02-22 23:15:59.739167	8	2026-02-22 23:15:59.739167	30	36bd9503-7a8a-4a00-acde-39d35c906b93
5bb51159-0e8b-48d3-8de6-8ca6599f64d4	không hayya	2026-02-22 23:16:17.736755	1	2026-02-22 23:16:17.736755	30	36bd9503-7a8a-4a00-acde-39d35c906b93
262f4bd9-132e-4279-92b6-4266ad283905	dỡ	2026-02-22 23:17:39.975796	1	2026-02-22 23:17:39.975796	10	36bd9503-7a8a-4a00-acde-39d35c906b93
8da54bbf-06be-47f0-85e7-80ef8034d233	dỡ tệ	2026-02-22 23:18:02.979208	1	2026-02-22 23:18:02.979208	10	36bd9503-7a8a-4a00-acde-39d35c906b93
0769ffe6-5785-4eef-bb14-d5aa41749295	hay	2026-02-22 23:18:19.542386	10	2026-02-22 23:18:19.542386	9	36bd9503-7a8a-4a00-acde-39d35c906b93
55af8715-2900-426e-b6b5-69a01ef70b71	\N	2026-02-22 23:18:54.357265	5	2026-02-22 23:18:54.357265	9	36bd9503-7a8a-4a00-acde-39d35c906b93
5efd6868-c067-47ed-8a77-2a68f39d94c8	\N	2026-02-22 23:19:30.678457	5	2026-02-22 23:19:30.678457	9	36bd9503-7a8a-4a00-acde-39d35c906b93
f763b068-d1da-41be-9a82-b65b40eac431	Hayy	2026-02-24 22:34:22.362752	10	2026-02-24 22:34:22.362752	2	36bd9503-7a8a-4a00-acde-39d35c906b93
5983bb8c-f4a6-4421-9251-d40e0f8bd912	oke	2026-02-24 22:34:33.06217	6	2026-02-24 22:34:33.06217	2	36bd9503-7a8a-4a00-acde-39d35c906b93
3767f9c9-321d-4a90-8fdb-82246ab02297	oke	2026-02-24 23:43:34.239332	6	2026-02-24 23:43:34.239332	2	a74a91c5-0edd-4102-a40c-f7bade9041a8
\.


--
-- TOC entry 5092 (class 0 OID 16576)
-- Dependencies: 228
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.genre (genre_id, created_at, name, updated_at) FROM stdin;
1	2025-11-29 16:34:27	Hài Hước	2025-11-29 16:34:27
2	2025-11-29 16:34:27	Hành Động	2025-11-29 16:34:27
3	2025-11-29 16:34:27	Phiêu Lưu	2025-11-29 16:34:27
4	2025-11-29 16:34:27	Chính Kịch	2025-11-29 16:34:27
5	2025-11-29 16:34:27	Tâm Lý	2025-11-29 16:34:27
6	2025-11-29 17:20:21	Bí Ẩn	2025-11-29 17:20:21
7	2025-11-29 17:26:31	Kinh Dị	2025-11-29 17:26:31
8	2025-11-29 17:29:04	Bí Ẩn (Trùng)	2025-11-29 17:29:04
9	2025-11-29 17:29:04	Khoa Học	2025-11-29 17:29:04
10	2025-11-29 17:29:04	Viễn Tưởng	2025-11-29 17:29:04
11	2025-11-29 17:46:24	Tình Cảm	2025-11-29 17:46:24
12	2025-11-29 17:49:21	Hình Sự	2025-11-29 17:49:21
\.


--
-- TOC entry 5088 (class 0 OID 16472)
-- Dependencies: 224
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice (invoice_id, created_at, end_date, payment_method, start_date, status, total_price, plan_id, profile_id) FROM stdin;
dc8d71d9-ee3d-46ce-b6ba-573dfc88e17c	2026-02-23 20:35:16.937373	2026-03-02	Credit Card	2026-02-23	completed	0.00	6	36bd9503-7a8a-4a00-acde-39d35c906b93
5426152a-0de4-40d4-a03c-a662745bd45d	2026-02-23 20:42:52.592161	2026-08-22	Credit Card	2026-02-23	completed	249.00	8	36bd9503-7a8a-4a00-acde-39d35c906b93
1151f050-6baf-4eb5-a6df-ccc9344e97f2	2026-02-23 20:57:57.155945	2027-02-23	Credit Card	2026-02-23	completed	499.00	5	36bd9503-7a8a-4a00-acde-39d35c906b93
48b25511-e34a-4b87-82ff-a1427c8ee730	2026-02-24 23:42:46.327367	2026-03-03	Credit Card	2026-02-24	completed	0.00	6	a74a91c5-0edd-4102-a40c-f7bade9041a8
\.


--
-- TOC entry 5089 (class 0 OID 16480)
-- Dependencies: 225
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie (movie_id, actor, age_rating, average_rating, country, created_at, description, director, duration, is_series, original_name, posterurl, poster_videourl, season, title, updated_at, view_count, year, favorite_count) FROM stdin;
4	Ayumu Murase...	13	0.00	Nhật Bản	2025-11-29 16:25:48	SANDA	Đang cập nhật	24 phút/tập	t	sanda	https://phimimg.com/upload/vod/20251005-1/76fc3f6c6f3222fd7d2fed2c423fb976.jpg	https://www.youtube.com/watch?v=PG9F1afLT-Y	1	SANDA	2026-01-04 09:42:21	1	2025	0
5	Joey Daud...	16	0.00	Malaysia	2025-11-29 16:25:48	Paraxnormal	Mohd Suzana Rosly	87 phút	f	para-x-normal	https://phimimg.com/upload/vod/20251129-1/11d088de8150fbb716383731959dec85.jpg	https://www.youtube.com/watch?v=4w1QTqqcn9E	0	Para X Normal	2025-12-23 05:42:28	0	2025	0
6	Đang cập nhật	18	0.00	Trung Quốc	2025-11-29 16:25:48	The Last Dynasty (The Chosen One)	Đang cập nhật	21 phút/tập	t	nam-dinh-coc-vi	https://phimimg.com/upload/vod/20251001-1/0ae28cf36937e4156c0104cc1aecf2ca.jpg	https://www.youtube.com/watch?v=C4kw5GSqLwU	1	Nam Đình Cốc Vi	2025-12-23 05:42:28	0	2025	0
11	Donnie Wahlberg...	16	0.00	Âu Mỹ	2025-11-29 16:25:48	Boston Blue	Brandon Margolis	43 phút/tập	t	so-canh-sat-boston	https://phimimg.com/upload/vod/20251020-1/d85bf3532a2fa3e7abe27d413c3d5a21.jpg	https://www.youtube.com/watch?v=hnhBEBCCWv0	1	Sở Cảnh Sát Boston	2026-01-05 10:18:10	1	2025	0
13	Đang cập nhật	13	0.00	Trung Quốc	2025-11-29 16:25:48	The Sword Emperor Of Eternity	Đang cập nhật	10 phút/tập	t	van-co-kiem-de	https://phimimg.com/upload/vod/20250823-1/8c5156e8121aacee7750e0701d18988d.jpg	https://www.youtube.com/watch?v=epjrKucJ2FI	1	Vạn Cổ Kiếm Đế	2025-12-23 05:42:28	0	2025	0
14	Đang cập nhật	16	0.00	Trung Quốc	2025-11-29 16:25:48	Overlord Of The Sky	Đang cập nhật	13 phút/tập	t	lang-thien-doc-ton	https://phimimg.com/upload/vod/20250719-1/b5864c72d2f3811c4a81cffcd67eaa4d.jpg	https://www.youtube.com/watch?v=En_Q0uFA34c	1	Lăng Thiên Độc Tôn	2026-01-31 10:34:21	1	2025	0
15	Đang cập nhật	18	0.00	Trung Quốc	2025-11-29 16:25:48	Ten Thousand Worlds	Đang cập nhật	10 phút/tập	t	van-gioi-doc-ton	https://phimimg.com/upload/vod/20250308-1/25c9c7486b5de820475bd8fe0ccb316e.jpg		1	Vạn Giới Độc Tôn	2025-12-23 05:42:28	0	2021	0
16	Đang cập nhật	13	0.00	Trung Quốc	2025-11-29 16:25:48	Eclipse Of Illusion	Đang cập nhật	25 phút/tập	t	van-tham-bat-tri-mong	https://phimimg.com/upload/vod/20250705-1/d9b4c7bf7cd7c0314e86e8d0ea065519.jpg	https://www.youtube.com/watch?v=d7kd5ZeF4os	1	Vân Thâm Bất Tri Mộng	2025-12-23 05:42:28	0	2025	0
17	Quách Phú Thành...	16	0.00	Hồng Kông	2025-11-29 16:25:48	City Under Siege	Trần Mộc Thắng	110 phút	f	toan-thanh-gioi-bi	https://phimimg.com/upload/vod/20251129-1/2874d4ad3ef220dd17d81b2fc1f6157b.jpg	https://www.youtube.com/watch?v=Mfdb7QsuM3w	0	Toàn Thành Giới Bị	2025-12-23 05:42:28	0	2010	0
18	Ryan Dillon...	18	0.00	Âu Mỹ	2025-11-29 16:25:48	Sesame Street The Nutcracker	Joanna Hepworth	27 phút	f	sesame-street-kep-hat-de	https://phimimg.com/upload/vod/20251129-1/c05adac0e1cfa14bef7ba777f10e6a80.jpg	https://www.youtube.com/watch?v=Ixlcp7Psa7o	0	Sesame Street Kẹp Hạt Dẻ	2025-12-23 05:42:28	0	2022	0
20	Leonardo DiCaprio...	16	0.00	Đức, Âu Mỹ	2025-11-29 16:25:48	The Aviator	Martin Scorsese	170 phút	f	phi-cong-ty-phu	https://phimimg.com/upload/vod/20251129-1/aa869350396407f7dc8dffdc7aa9cb87.jpg	https://www.youtube.com/watch?v=FebPJlmgldE	0	Phi Công Tỷ Phú	2025-12-23 05:42:28	0	2004	0
21	Dương Thiên Hoa...	18	0.00	Hồng Kông	2025-11-29 16:25:48	Perfect Wedding	Barbara Wong	99 phút	f	dam-cuoi-hoan-hao	https://phimimg.com/upload/vod/20251129-1/256c60b25a9e914577b14d92b3b5e783.jpg	https://www.youtube.com/watch?v=1MmJFySjWkw	0	Đám Cưới Hoàn Hảo	2026-01-05 11:11:42	1	2010	0
22	Mã Sĩ Viện...	13	0.00	Singapore	2025-11-29 16:25:48	Left-Handed Girl	Trâu Thời Kình	109 phút	f	co-gai-tay-chieu	https://phimimg.com/upload/vod/20251129-1/21b5fd705cbd66bf1ac0019b37b4fcea.jpg	https://www.youtube.com/watch?v=0rXnpfzpk8s	0	Cô Gái Tay Chiêu	2025-12-23 05:42:28	0	2025	0
23	Quan Ân Na...	16	0.00	Hồng Kông	2025-11-29 16:25:48	Short Of Love	Nguyễn Thế Sinh	105 phút	f	ai-tu-da-tinh	https://phimimg.com/upload/vod/20251129-1/e8ce551fcb143a8e98516a8d259c31cb.jpg	https://www.youtube.com/watch?v=S1IZqRzAn6g	0	Ái Tử Đa Tình	2025-12-23 05:42:28	0	2009	0
24	Damian Hardung...	18	0.00	Đức	2025-11-29 16:25:48	Maxton Hall: The World Between Us (Season 2)	Martin Schreier	47 phút/tập	t	maxton-hall-the-gioi-giua-chung-ta	https://phimimg.com/upload/vod/20251116-1/bb3a3a56df2199f635e37424c2ae34b5.jpg	https://www.youtube.com/watch?v=HJzSlErp1vw	2	Maxton Hall: Thế Giới Giữa Chúng Ta (Phần 2)	2025-12-23 05:42:28	0	2025	0
8	Suzanne Lindon...	16	0.00	Pháp, Bỉ	2025-11-29 16:25:48	Colours Of Time	Cédric Klapisch	126 phút	f	mau-thoi-gian-la-venue-de-l-avenir	https://phimimg.com/upload/vod/20251129-1/81edf4c9fbb3fd7214c3b402a9d102b1.jpg	https://www.youtube.com/watch?v=_sTdOm2BID8	0	Màu Thời Gian (La Venue de l'avenir)	2025-12-23 05:42:28	0	2025	1
19	Giang Nhược Lâm...	13	10.00	Hồng Kông	2025-11-29 16:25:48	Seven 2 One	Bành Phát	85 phút	f	quan-nhan-that-su	https://phimimg.com/upload/vod/20251129-1/c78dc4ea1942c3381d2479a6d4b83a21.jpg	https://www.youtube.com/watch?v=3cy0PBuBKwg	0	Quan Nhân Thất Sự	2025-12-23 08:13:59	14	2009	1
3	Minori Fujidera...	18	0.00	Nhật Bản	2025-11-29 16:25:48	A Star Brighter Than The Sun	Đang cập nhật	24 phút/tập	t	taiyou-yori-mo-mabushii-hoshi	https://phimimg.com/upload/vod/20251011-1/3da453d1118985d058f0862da029e415.jpg	https://www.youtube.com/watch?v=ASdWWKeNBlI	1	Taiyou yori mo Mabushii Hoshi	2026-01-05 10:13:16	4	2025	1
7	Đang cập nhật	13	0.00	Trung Quốc	2025-11-29 16:25:48	One Hundred Thousand Years of Qi Refining	Đang cập nhật	12 phút/tập	t	luyen-khi-muoi-van-nam	https://phimimg.com/upload/vod/20250219-1/0ddffeb590aa6bad556a0724302487ad.jpg		1	Luyện Khí Mười Vạn Năm	2025-12-23 05:42:28	0	2023	3
12	Đang cập nhật	18	0.00	Trung Quốc	2025-11-29 16:25:48	The Death-Defying Divine Emperor	Đang cập nhật	10 phút/tập	t	tuyet-the-than-hoang	https://phimimg.com/upload/vod/20250921-1/6af42581e2589833f2c55ab0f4b1fe36.jpg		1	Tuyệt Thế Thần Hoàng	2025-12-23 05:42:28	0	2025	1
10	Clarissa Cozzoni...	13	5.50	Âu Mỹ	2025-11-29 16:25:48	Minding Your Business	David Kloehr, Brandon Thomas	85 phút	f	viec-ai-nay-lo	https://phimimg.com/upload/vod/20251129-1/98aaed9c5d153f75d145b768311ba3e5.jpg	https://www.youtube.com/watch?v=95WdxuKGlp8	0	Việc Ai Nấy Lo	2026-01-05 10:33:22	7	2025	2
2	Jack Patten, Sean Bean...	16	7.33	Âu Mỹ	2025-11-29 16:25:48	Robin Hood	Jonathan English	59 phút/tập	t	robin-hood	https://phimimg.com/upload/vod/20251102-1/ee364c67f40e6015076b4d704fd75ee2.jpg	https://www.youtube.com/watch?v=SDfWMSNQEJc	1	Robin Hood	2026-01-03 13:04:40	7	2025	0
25	Qian Wenqing...	13	7.00	Trung Quốc	2025-11-29 16:25:48	A Record of a Mortal's Journey to Immortality	Đang cập nhật	20 phút/tập	t	pham-nhan-tu-tien	https://phimimg.com/upload/vod/20240715-1/ed827a6e5d8f7c41aa0728403665cd87.jpg	https://www.youtube.com/watch?v=WSzxfY5OHG0	1	Phàm Nhân Tu Tiên	2026-01-04 09:39:52	0	2020	0
26	Minami Takayama...	16	0.00	Nhật Bản	2025-11-29 16:25:48	Detective Conan: One-Eyed Flashback	Katsuya Shigehara	109 phút	f	tham-tu-lung-danh-conan-28-du-anh-cua-doc-nhan	https://phimimg.com/upload/vod/20250827-1/aabb76a70d8afd21ddf2be37381c7587.jpg	https://www.youtube.com/watch?v=LQYpLcz53pQ	0	Thám Tử Lừng Danh Conan 28: Dư Ảnh Của Độc Nhãn	2025-12-23 05:42:28	0	2025	0
27	Shuichiro Umeda...	18	0.00	Nhật Bản	2025-11-29 16:25:48	Watari-kun's ****** Is About to Collapse	Đang cập nhật	24 phút/tập	t	watari-kun-no-xx-ga-houkai-sunzen	https://phimimg.com/upload/vod/20250714-1/535880cad32d19a82a93a06de3611bfe.jpg	https://www.youtube.com/watch?v=ZBL5qN7mmdg	1	Watari-kun no xx ga Houkai Sunzen	2025-12-23 05:42:28	0	2025	0
29	Manaka Iwami...	16	0.00	Nhật Bản	2025-11-29 16:25:48	With You, Our Love Will Make It Through	Đang cập nhật	22 phút/tập	t	voi-em-tinh-yeu-cua-chung-ta-se-vuot-qua	https://phimimg.com/upload/vod/20251024-1/317ff0dad1c7b41b206dfc469aae1913.jpg	https://www.youtube.com/watch?v=THtWMgfQko4	1	Với Em, Tình Yêu Của Chúng Ta Sẽ Vượt Qua	2026-01-05 10:15:25	1	2025	0
31	Damian Hardung...	13	0.00	Đức	2024-05-14 17:53:33	Khi Ruby vô tình chứng kiến một bí mật...	Martin Schreier	47 phút/tập	t	maxton-hall-the-gioi-giua-chung-ta	https://phimimg.com/upload/vod/20251116-1/36374493acce1b82e83a2335a6fc4ce4.jpg	https://www.youtube.com/watch?v=wDqfLhN43Kw	1	Maxton Hall: Thế Giới Giữa Chúng Ta (Phần 1)	2025-12-23 05:42:28	0	2024	0
28	Lorena Schuett...	13	0.00	Thái Lan	2025-11-29 16:25:48	My Safe Zone	Đang cập nhật	45 phút/tập	t	vung-an-toan-cua-em	https://phimimg.com/upload/vod/20251026-1/f5387e7686a4a4e164c893c3d1cc3896.jpg	https://www.youtube.com/watch?v=6b4UnDCGYsI	1	Vùng An Toàn Của Em	2026-01-05 11:06:07	4	2025	0
1	Non, Taisuke Fujigaya...	13	2.30	Nhật Bản	2025-11-29 16:25:48	Happy Kanako's Killer Life	Tsutomu Hanabusa	20 phút/tập	t	cuoc-song-sat-thu-cua-kanako-hanh-phuc	https://phimimg.com/upload/vod/20251129-1/ab5dd50a62987a8b949dcb545f29902d.jpg	https://www.youtube.com/watch?v=oufb1Lu0JFw	1	Cuộc Sống Sát Thủ Của Kanako Hạnh Phúc	2026-01-05 10:31:57	18	2025	1
30	Nina Tamaki...	18	8.25	Nhật Bản	2025-11-29 16:25:48	My Gift Lvl 9999 Unlimited Gacha...	Đang cập nhật	24 phút/tập	t	vo-han-gacha	https://phimimg.com/upload/vod/20251004-1/34fa41b21a6beb9aa5dbcf377c9c6e88.jpg	https://www.youtube.com/watch?v=sV-bHXAmOEM	1	Vô Hạn Gacha	2026-01-05 11:16:29	6	2025	0
9	Rose Byrne...	18	7.50	Âu Mỹ	2025-11-29 16:25:48	If I Had Legs I'd Kick You	Mary Bronstein	113 phút	f	neu-toi-co-chan-toi-se-da-co-duong-cung-ngo-cut	https://phimimg.com/upload/vod/20251129-1/84688bd9eba87f9dcc40072c8a70629e.jpg	https://www.youtube.com/watch?v=-oDLBRLkflo	0	Nếu Tôi Có Chân, Tôi Sẽ Đá Cô (Đường Cùng Ngõ Cụt)	2025-12-23 05:42:28	0	2025	0
\.


--
-- TOC entry 5093 (class 0 OID 16584)
-- Dependencies: 229
-- Data for Name: movie_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie_genre (movie_genre_id, genre_id, movie_id) FROM stdin;
1	1	1
2	2	1
3	3	1
4	4	1
5	5	1
6	2	2
7	3	2
8	4	2
9	5	2
10	1	3
11	4	3
12	5	3
13	2	4
14	3	4
15	4	4
16	5	4
17	6	4
18	1	5
19	4	5
20	5	5
21	7	5
22	8	6
23	9	6
24	10	6
25	4	6
26	5	6
27	2	7
28	3	7
29	9	7
30	10	7
31	4	8
32	1	8
33	5	8
34	4	9
35	5	9
36	11	9
37	11	10
38	1	10
39	5	10
40	12	11
41	4	11
42	5	11
43	4	12
44	5	12
49	2	13
50	3	13
51	4	13
52	5	13
53	3	14
54	4	14
55	10	14
56	2	15
57	3	15
58	4	15
59	9	15
60	10	15
61	3	16
62	4	16
63	5	16
64	10	16
65	2	17
66	5	17
67	9	17
68	10	17
69	4	19
70	5	19
71	12	19
75	3	20
76	4	20
77	5	20
78	11	21
79	1	21
80	5	21
81	4	21
82	11	22
83	4	22
84	5	22
85	1	23
86	4	23
87	11	23
88	5	23
89	4	24
90	11	24
91	5	24
92	2	26
93	8	26
94	12	26
95	5	26
96	1	27
97	4	27
98	5	27
99	4	28
100	11	28
101	5	28
102	4	29
103	9	29
104	10	29
105	11	29
106	5	29
107	2	30
108	3	30
109	9	30
110	10	30
111	4	30
112	5	30
113	4	31
114	11	31
115	5	31
\.


--
-- TOC entry 5090 (class 0 OID 16488)
-- Dependencies: 226
-- Data for Name: plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan (plan_id, created_at, duration_days, name, price, updated_at) FROM stdin;
5	2025-11-29 18:25:40	365	Gói VIP	499.00	2025-11-29 18:25:40
6	2025-11-29 18:25:40	7	Gói Thử Nghiệm	0.00	2025-11-29 18:25:40
8	2025-11-29 18:25:40	180	Gói Nâng Cao	249.00	2025-11-29 18:25:40
9	2025-11-29 18:25:40	30	Gói Tiết Kiệm	79.00	2025-11-29 18:25:40
\.


--
-- TOC entry 5083 (class 0 OID 16414)
-- Dependencies: 219
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (profile_id, dob, email, first_name, last_name, phone_number, user_id, username, create_at, gender, interest, update_at, avatar, plan_id, expert_time_plan) FROM stdin;
36bd9503-7a8a-4a00-acde-39d35c906b93	2000-02-22	vothanhhuy@gmail.com	Võ	Thanh Huy 710	0362529392	e0ae5d62-fa3f-45f0-8735-9e7aab33b144	thanhhuy	2026-02-20 19:46:41.989447	\N	\N	2026-02-24 20:14:46.068857		5	2027-08-29 20:35:16.909452
62d5b274-3d42-4d89-815c-eb986942d21c	2015-10-14	thaihuy@gmail.com	Mè	Thái	\N	588a2070-21c5-429b-8ea5-451ca86b300f	thaihuy	2026-02-24 22:08:38.975258	\N	\N	2026-02-24 22:08:38.977704	\N	\N	\N
a74a91c5-0edd-4102-a40c-f7bade9041a8	2000-04-08	hoangduong@gmail.com	Hoang	Duong	0896612244	45be3936-6558-4dfd-a7c8-7d254657b327	hoangduong	2026-02-20 19:47:06.059099	\N	\N	2026-02-24 23:43:16.024934		6	2026-03-03 23:42:46.280514
\.


--
-- TOC entry 5091 (class 0 OID 16496)
-- Dependencies: 227
-- Data for Name: view; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.view (view_id, progress, viewed_at, episode_id, movie_id, profile_id) FROM stdin;
5594a451-f382-40b6-8aed-0d60143beebc	45	2026-02-22 18:05:48.122031	\N	1	36bd9503-7a8a-4a00-acde-39d35c906b93
619d47cf-437c-4319-bd77-80fc937e05b5	10	2026-02-22 19:45:45.784201	\N	30	36bd9503-7a8a-4a00-acde-39d35c906b93
d7701dca-1c9c-4420-acf7-d036743eb93a	45	2026-02-22 19:46:29.707043	\N	1	36bd9503-7a8a-4a00-acde-39d35c906b93
fb821f5c-8921-4888-856d-2afe13415a8f	45	2026-02-22 19:47:50.506213	\N	2	36bd9503-7a8a-4a00-acde-39d35c906b93
dcfd9306-4882-4cba-9398-bb8631baa65f	45	2026-02-22 19:48:35.883998	\N	2	36bd9503-7a8a-4a00-acde-39d35c906b93
d9d5cf76-133b-446d-8f19-be477ff3a89a	10	2026-02-22 23:21:50.065761	\N	10	36bd9503-7a8a-4a00-acde-39d35c906b93
6828f076-c657-4342-94f5-017ded456309	10	2026-02-22 23:22:54.30035	\N	10	36bd9503-7a8a-4a00-acde-39d35c906b93
15d31f3b-ebe3-4468-96da-e5214acecf99	45	2026-02-22 23:27:14.150614	\N	2	36bd9503-7a8a-4a00-acde-39d35c906b93
e40a0744-6c90-420f-bbbb-4d04a6805b3d	45	2026-02-22 23:27:16.774218	\N	2	36bd9503-7a8a-4a00-acde-39d35c906b93
0a5cd045-2405-46fb-a308-3e4f7964c9c1	45	2026-02-22 23:27:22.738886	\N	2	36bd9503-7a8a-4a00-acde-39d35c906b93
ef7a1ebd-2895-46ae-9906-714c6bdbdf52	10	2026-02-22 23:28:14.221291	\N	28	36bd9503-7a8a-4a00-acde-39d35c906b93
61a0a8bc-766c-4886-9309-a27512e51cbf	10	2026-02-22 23:28:39.654273	\N	28	36bd9503-7a8a-4a00-acde-39d35c906b93
\.


--
-- TOC entry 4900 (class 2606 OID 16431)
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (cart_id);


--
-- TOC entry 4902 (class 2606 OID 16439)
-- Name: episode episode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT episode_pkey PRIMARY KEY (episode_id);


--
-- TOC entry 4904 (class 2606 OID 16447)
-- Name: favorite favorite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT favorite_pkey PRIMARY KEY (favorite_id);


--
-- TOC entry 4908 (class 2606 OID 16455)
-- Name: feedback feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (feedback_id);


--
-- TOC entry 4918 (class 2606 OID 16583)
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (genre_id);


--
-- TOC entry 4910 (class 2606 OID 16479)
-- Name: invoice invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (invoice_id);


--
-- TOC entry 4920 (class 2606 OID 16591)
-- Name: movie_genre movie_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT movie_genre_pkey PRIMARY KEY (movie_genre_id);


--
-- TOC entry 4912 (class 2606 OID 16487)
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (movie_id);


--
-- TOC entry 4914 (class 2606 OID 16495)
-- Name: plan plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan
    ADD CONSTRAINT plan_pkey PRIMARY KEY (plan_id);


--
-- TOC entry 4896 (class 2606 OID 16421)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (profile_id);


--
-- TOC entry 4898 (class 2606 OID 16423)
-- Name: profile uk_c1dkiawnlj6uoe6fnlwd6j83j; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT uk_c1dkiawnlj6uoe6fnlwd6j83j UNIQUE (user_id);


--
-- TOC entry 4906 (class 2606 OID 16505)
-- Name: favorite ukon1k82euye86l9jtgh4wryjj1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT ukon1k82euye86l9jtgh4wryjj1 UNIQUE (profile_id, movie_id);


--
-- TOC entry 4916 (class 2606 OID 16503)
-- Name: view view_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view
    ADD CONSTRAINT view_pkey PRIMARY KEY (view_id);


--
-- TOC entry 4931 (class 2606 OID 16566)
-- Name: view fk12s66m84eav7mbgq4bcxsthot; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view
    ADD CONSTRAINT fk12s66m84eav7mbgq4bcxsthot FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 4932 (class 2606 OID 16561)
-- Name: view fk2qja05xk5vhkecn9woxq9647g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view
    ADD CONSTRAINT fk2qja05xk5vhkecn9woxq9647g FOREIGN KEY (episode_id) REFERENCES public.episode(episode_id);


--
-- TOC entry 4921 (class 2606 OID 16806)
-- Name: profile fk45ek576m2pp6ts42whuk1ceq0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT fk45ek576m2pp6ts42whuk1ceq0 FOREIGN KEY (plan_id) REFERENCES public.plan(plan_id);


--
-- TOC entry 4927 (class 2606 OID 16536)
-- Name: feedback fk545ruox7xt3q20yunedcasfqj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fk545ruox7xt3q20yunedcasfqj FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 4925 (class 2606 OID 16521)
-- Name: favorite fk728jcsp8okxo8km8tly6q8lku; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fk728jcsp8okxo8km8tly6q8lku FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 4922 (class 2606 OID 16506)
-- Name: cart fk78kstfs9gpb2dbpbjkc9cv7ty; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk78kstfs9gpb2dbpbjkc9cv7ty FOREIGN KEY (plan_id) REFERENCES public.plan(plan_id);


--
-- TOC entry 4934 (class 2606 OID 16592)
-- Name: movie_genre fk86p3roa187k12avqfl28klp1q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT fk86p3roa187k12avqfl28klp1q FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id);


--
-- TOC entry 4928 (class 2606 OID 16531)
-- Name: feedback fk9m00odcict7tnhbmavykhev76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fk9m00odcict7tnhbmavykhev76 FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 4926 (class 2606 OID 16526)
-- Name: favorite fkbacg9rtul0wcu8rfkoi5p9t6n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fkbacg9rtul0wcu8rfkoi5p9t6n FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 4933 (class 2606 OID 16571)
-- Name: view fkcctpdyc649ok9ldnknt9prdp0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view
    ADD CONSTRAINT fkcctpdyc649ok9ldnknt9prdp0 FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 4929 (class 2606 OID 16551)
-- Name: invoice fkk87sloosh4i9efog61eviwseo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fkk87sloosh4i9efog61eviwseo FOREIGN KEY (plan_id) REFERENCES public.plan(plan_id);


--
-- TOC entry 4935 (class 2606 OID 16597)
-- Name: movie_genre fkp6vjabv2e2435at1hnuxg64yv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT fkp6vjabv2e2435at1hnuxg64yv FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 4924 (class 2606 OID 16516)
-- Name: episode fksbpb6q6d7t2jvwfwmlp0953e4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT fksbpb6q6d7t2jvwfwmlp0953e4 FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 4930 (class 2606 OID 16556)
-- Name: invoice fksmy3ftsp7rr7qm1n980f6gecn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fksmy3ftsp7rr7qm1n980f6gecn FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 4923 (class 2606 OID 16511)
-- Name: cart fkt7pdlpp5u5ge7vvi6s5dntfb4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkt7pdlpp5u5ge7vvi6s5dntfb4 FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


-- Completed on 2026-02-25 03:06:21

--
-- PostgreSQL database dump complete
--

\unrestrict JNgdvDuLcd9TkFbOs5saL2KKvZwZvxCpv1IqdZ6dhnE0yf6stoFetEV6JbsseNV

