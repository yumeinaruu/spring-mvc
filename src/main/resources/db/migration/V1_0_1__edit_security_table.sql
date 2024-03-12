DROP TABLE public.security;
create table public.security
(
    id       bigserial
        constraint security_pk
            primary key,
    login    varchar(10)                                   not null,
    password varchar(10)                                   not null,
    user_id  bigint                                        not null
        constraint user_id___fk
            references public.users
            on update cascade on delete cascade,
    role     varchar(10) default 'USER'::character varying not null
);

