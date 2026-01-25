--liquibase formatted sql
--changeset kserg:initial-1
--comment: Database initialization

CREATE TABLE IF NOT EXISTS public.admin
(
    id bigint NOT NULL,
    CONSTRAINT admin_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.booking
(
    chat_id bigint NOT NULL,
    client_id bigint NOT NULL,
    end_time timestamp(6) with time zone,
    id bigint NOT NULL,
    place_id bigint NOT NULL,
    price_plan_id bigint NOT NULL,
    start_time timestamp(6) with time zone,
    additions character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT booking_pkey PRIMARY KEY (id),
    CONSTRAINT booking_chat_id_key UNIQUE (chat_id),
    CONSTRAINT booking_place_id_key UNIQUE (place_id)
);

CREATE TABLE IF NOT EXISTS public.chat
(
    client_id bigint NOT NULL,
    id bigint NOT NULL,
    owner_id bigint NOT NULL,
    CONSTRAINT chat_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.client
(
    id bigint NOT NULL,
    CONSTRAINT client_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.comment
(
    commented_space_id bigint NOT NULL,
    created_at timestamp(6) with time zone,
    id bigint NOT NULL,
    parent_comment_id bigint,
    person_id bigint NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT comment_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.comment_parent
(
    comment_id bigint NOT NULL,
    parent_id bigint NOT NULL,
    CONSTRAINT comment_parent_parent_id_key UNIQUE (parent_id)
);

CREATE TABLE IF NOT EXISTS public.coworking_place
(
    is_free boolean NOT NULL,
    id bigint NOT NULL,
    space_id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT coworking_place_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.credentials
(
    enable boolean NOT NULL,
    id bigint NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT credentials_pkey PRIMARY KEY (id),
    CONSTRAINT credentials_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS public.message
(
    is_changed boolean NOT NULL,
    is_read boolean NOT NULL,
    chat_id bigint NOT NULL,
    id bigint NOT NULL,
    send_time timestamp(6) with time zone,
    sender_id bigint NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT message_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.owner
(
    id bigint NOT NULL,
    CONSTRAINT owner_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.person
(
    id bigint NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    secondname character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.price_plan
(
    price numeric(38, 2),
    id bigint NOT NULL,
    space_id bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT price_plan_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.review
(
    rate integer,
    created_at timestamp(6) with time zone,
    id bigint NOT NULL,
    reviewed_space_id bigint NOT NULL,
    reviewer_id bigint NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT review_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.space
(
    rating numeric(38, 2),
    id bigint NOT NULL,
    owner_id bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT space_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.admin
    ADD CONSTRAINT fkggyoc9dpl6afp136a55fxtnvq FOREIGN KEY (id)
    REFERENCES public.credentials (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS admin_pkey
    ON public.admin(id);


ALTER TABLE IF EXISTS public.booking
    ADD CONSTRAINT fkac41gum9v61avgk0pqshk3n8n FOREIGN KEY (chat_id)
    REFERENCES public.chat (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS booking_chat_id_key
    ON public.booking(chat_id);


ALTER TABLE IF EXISTS public.booking
    ADD CONSTRAINT fkfgea2k7ts9mck9es9qw8vr7s4 FOREIGN KEY (price_plan_id)
    REFERENCES public.price_plan (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.booking
    ADD CONSTRAINT fkhs7eej4m2orrmr5cfbcrqs8yw FOREIGN KEY (client_id)
    REFERENCES public.client (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.booking
    ADD CONSTRAINT fkji1jqa46ac9emryqdihmqai5x FOREIGN KEY (place_id)
    REFERENCES public.coworking_place (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS booking_place_id_key
    ON public.booking(place_id);


ALTER TABLE IF EXISTS public.chat
    ADD CONSTRAINT fk358dv3lgqrt6wea00v7gxi9q4 FOREIGN KEY (owner_id)
    REFERENCES public.owner (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.chat
    ADD CONSTRAINT fkrrnwynvawopofurxfkbgtrame FOREIGN KEY (client_id)
    REFERENCES public.client (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.client
    ADD CONSTRAINT fkr1e0j10i9v9i52l6tqfa69nj0 FOREIGN KEY (id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS client_pkey
    ON public.client(id);


ALTER TABLE IF EXISTS public.comment
    ADD CONSTRAINT fk285svcgstjk3k94kv412gce6x FOREIGN KEY (person_id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.comment
    ADD CONSTRAINT fkk08g38lim1py4nkhlv3lll1qt FOREIGN KEY (commented_space_id)
    REFERENCES public.space (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.coworking_place
    ADD CONSTRAINT fk4dhq0wbcx86t6rkkcm1ritqfe FOREIGN KEY (space_id)
    REFERENCES public.space (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.message
    ADD CONSTRAINT fk6p9dy39ram5rqdl84nciff92j FOREIGN KEY (sender_id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.message
    ADD CONSTRAINT fkmejd0ykokrbuekwwgd5a5xt8a FOREIGN KEY (chat_id)
    REFERENCES public.chat (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.owner
    ADD CONSTRAINT fk8l2ymuksx0e53ouw99wxl5uu6 FOREIGN KEY (id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS owner_pkey
    ON public.owner(id);


ALTER TABLE IF EXISTS public.person
    ADD CONSTRAINT fked0y2vwiva7xxw643ved2wj94 FOREIGN KEY (id)
    REFERENCES public.credentials (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS person_pkey
    ON public.person(id);


ALTER TABLE IF EXISTS public.price_plan
    ADD CONSTRAINT fkkni82pdtq83stwhi4lcs51cpd FOREIGN KEY (space_id)
    REFERENCES public.space (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.review
    ADD CONSTRAINT fk9499jd8tx5fi5cjmulg6i4muk FOREIGN KEY (reviewer_id)
    REFERENCES public.client (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.review
    ADD CONSTRAINT fkt9t8ca9j6d3dsn0iaw4iki1c1 FOREIGN KEY (reviewed_space_id)
    REFERENCES public.space (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.space
    ADD CONSTRAINT fk37y0rly9kkspqmdi3wy35xp4p FOREIGN KEY (owner_id)
    REFERENCES public.owner (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;