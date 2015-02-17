CREATE TABLE users (
  username varchar(45) PRIMARY KEY,
  password varchar(450) NOT NULL,
  enabled bit(1) NOT NULL DEFAULT '1'
);

CREATE TABLE user_roles (
  user_role_id int(11) PRIMARY KEY AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  ROLE varchar(45) NOT NULL,
  UNIQUE KEY uni_username_role (ROLE, username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
  ('andrew', 'andrew@123', 1),
  ('robert', 'robert@123', 1);

INSERT INTO `user_roles` (`user_role_id`, `username`, `ROLE`) VALUES
  (2, 'andrew', 'ROLE_ADMIN'),
  (1, 'andrew', 'ROLE_USER'),
  (3, 'robert', 'ROLE_USER');

