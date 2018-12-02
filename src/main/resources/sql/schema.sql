CREATE TABLE merchants (
  id INT NOT NULL AUTO_INCREMENT,
  email varchar(150) NOT NULL,
  password varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT users UNIQUE (email)
);

