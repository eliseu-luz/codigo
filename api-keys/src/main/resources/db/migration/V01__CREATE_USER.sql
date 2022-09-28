CREATE TABLE public."user" (
    id serial primary key,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    filial integer,
    requires_validation boolean,
    active boolean
);
