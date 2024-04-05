Create table events
(
    id                varchar(255) not null primary key,
    title             varchar(255) not null,
    details           varchar(255) not null,
    SLUG               varchar(255) not null,
    maximun_attendees integer      not null,
);