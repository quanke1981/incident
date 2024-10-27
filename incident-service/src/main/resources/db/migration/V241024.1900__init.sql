CREATE TABLE incident (
    id bigint auto_increment primary key,
    title varchar(32),
    description varchar(2000),
    status varchar(20)
)