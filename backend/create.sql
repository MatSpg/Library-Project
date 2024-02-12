    create table books (
        author_id integer,
        date date,
        id integer not null auto_increment,
        cover varchar(255),
        description varchar(255),
        isbn varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users (
        birthdate date,
        id integer not null auto_increment,
        biography varchar(255),
        image varchar(255),
        name varchar(255),
        password varchar(255),
        surname varchar(255),
        username varchar(255),
        favourite_books varbinary(255),
        primary key (id)
    ) engine=InnoDB;
