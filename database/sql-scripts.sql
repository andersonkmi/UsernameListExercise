CREATE TABLE user (
	user_name varchar(15) not null primary key, 
	first_name varchar(30) not null, 
	last_name varchar(30) not null
);

INSERT INTO user(user_name, first_name, last_name) VALUES ('anderson', 'Anderson', 'Ito');
INSERT INTO user(user_name, first_name, last_name) VALUES ('anderson1', 'Anderson', 'Ito');
INSERT INTO user(user_name, first_name, last_name) VALUES ('superuser', 'Super', 'user');

CREATE TABLE forbidden_words (
	id int not null primary key;
	word varchar(50) not null unique
);

INSERT INTO forbidden_words(word) VALUES ('crack');