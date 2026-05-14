create table authors (
    id bigint auto_increment primary key,
    firstname varchar(100),
    lastname varchar(100),
    email varchar(100)
);

create table posts (
    id bigint auto_increment primary key,
    title varchar(100) not null,
    body varchar(100) not null,
    publish_date char(8),
    author_id bigint,
    foreign key(author_id) references authors(id)
);

create table comments(
    id bigint auto_increment primary key,
    email varchar(100) not null,
    body varchar(200) not null,
    date char(8),
    post_id bigint,
    foreign key(post_id) references posts(id)
);