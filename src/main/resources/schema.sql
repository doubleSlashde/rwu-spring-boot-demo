create table if not exists student
(
    id         bigint not null primary key,
    first_name varchar(255),
    last_name  varchar(255),
    street     varchar(255),
    house_nr   varchar(255),
    city       varchar(255)
);

alter table student
    owner to postgres;

create sequence if not exists seq_student;

alter sequence seq_student owner to postgres;
