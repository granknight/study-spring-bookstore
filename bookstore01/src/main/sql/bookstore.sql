create table books (
  id Integer AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  author varchar(50) NOT NULL,
  publishDate timestamp NOT NULL,
  comment varchar(255),
  status Integer NOT NULL,
  rentUserId Integer
);

create table users (
    id Integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    password varchar(12) NOT NULL,
    point Integer NOT NULL,
    level Integer NOT NULL
);
ALTER TABLE bookstore.books ADD CONSTRAINT books_users_FK FOREIGN KEY (rentUserId)
      REFERENCES bookstore.users(id) ON DELETE SET NULL;

create table histories (
    id Integer AUTO_INCREMENT PRIMARY KEY,
    userId Integer NOT NULL,
    bookId Integer NOT NULL,
    actionType Integer NOT NULL,
    insertDate timestamp NOT NULL
);
ALTER TABLE bookstore.histories ADD CONSTRAINT history_userFK FOREIGN KEY (userId) REFERENCES bookstore.users(id);
ALTER TABLE bookstore.histories ADD CONSTRAINT history_bookFK FOREIGN KEY (bookId) REFERENCES bookstore.books(id);