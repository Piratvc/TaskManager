
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


CREATE TABLE public.roles (
    id bigint NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;


CREATE TABLE public.status (
    id bigint NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.status OWNER TO postgres;


CREATE TABLE public.task_records (
    id bigint NOT NULL,
    name text NOT NULL,
    user_fk bigint NOT NULL,
    statusname_fk bigint NOT NULL,
    "time" timestamp without time zone
);


ALTER TABLE public.task_records OWNER TO postgres;



ALTER TABLE public.task_records ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.task_records_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);




CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

CREATE TABLE public.users (
    id bigint NOT NULL,
    name text NOT NULL,
    username text NOT NULL,
    password text NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;


ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 12
    INCREMENT BY 1
    MINVALUE 12
    NO MAXVALUE
    CACHE 1
);



COPY public.roles (id, name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USER
\.




COPY public.status (id, name) FROM stdin;
1	RENDERING
2	COMPLETE
\.




COPY public.task_records (id, name, user_fk, statusname_fk, "time") FROM stdin;
26	Задача 56	2	1	2022-11-23 14:12:22.944
27	Задача 56	2	2	2022-11-23 14:12:53.363
28	Задача 21	2	1	2022-11-23 17:44:06.177
29	Задача 21	2	2	2022-11-23 17:44:36.799
30	Задача 	1	1	2022-11-23 18:16:54.426
31	Задача 	1	2	2022-11-23 18:17:30.639
32	Задача 13	13	1	2022-11-23 18:59:49.373
33	Задача 13	13	2	2022-11-23 19:00:25.006
\.



COPY public.user_roles (user_id, role_id) FROM stdin;
1	1
2	2
1	2
12	1
13	2
14	2
\.




COPY public.users (id, name, username, password) FROM stdin;
1	Сергей	serg	$2a$04$u9aNkJyfle76RoxSTlsuNeIbWtUe2VNSmoq2pzLGtPSqSOkceCyRm
2	Виталий	vita	$2a$04$u9aNkJyfle76RoxSTlsuNeIbWtUe2VNSmoq2pzLGtPSqSOkceCyRm
12	Иван	ivan	$2a$10$33lMEzl/tyBPdZFQjsfULOJZ5wvOhyBSAogGB/vf857m/yCTzi7U2
13	Алексей	alex	$2a$10$shOEXe1Q4r6kU9nGeobWsul.RUmSrztt8CqpPS3J5WFvYJ1u6rabS
14	Васян	vitamin	$2a$10$sq5lviVicZzpHMpeg8iIFuOt8FarmZ1i.iKYrN9/DCRvgJCWmW/N2
\.



SELECT pg_catalog.setval('public.task_records_id_seq', 33, true);



SELECT pg_catalog.setval('public.users_id_seq', 14, true);



ALTER TABLE ONLY public.status
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey1 PRIMARY KEY (id);




ALTER TABLE ONLY public.task_records
    ADD CONSTRAINT task_records_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);




ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.task_records
    ADD CONSTRAINT task_records_statusname_fkey FOREIGN KEY (statusname_fk) REFERENCES public.status(id);




ALTER TABLE ONLY public.task_records
    ADD CONSTRAINT task_records_user_fkey FOREIGN KEY (user_fk) REFERENCES public.users(id);




ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


