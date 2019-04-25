SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS  enrollee, subjectType, subject,
faculty, facultySubjectType,request, statement, facultysubject, enrolleeSubject, statementRequests;
 SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE enrollee(
	id INT AUTO_INCREMENT NOT NULL,
	firstName VARCHAR(15) NOT NULL,
	lastName VARCHAR(15) NOT NULL,
	login VARCHAR(15) NOT NULL,
	password VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(email),
	UNIQUE(login)
);

CREATE TABLE subjectType(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(15) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE subject(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	subjectTypeId INT NOT NULL,
	PRIMARY KEY(id, subjectTypeId),
	FOREIGN KEY(subjectTypeId) REFERENCES subjectType(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE enrolleeSubject(
	id INT AUTO_INCREMENT NOT NULL,
	enrolleeId INT NOT NULL,
	subjectId INT NOT NULL,
	point FLOAT NOT NULL,
	PRIMARY KEY(id, enrolleeId, subjectId),
	FOREIGN KEY(enrolleeId) REFERENCES enrollee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(subjectId) REFERENCES subject(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE faculty(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	generalCount INT NOT NULL,
	budgetCount INT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE facultySubject(
	id INT AUTO_INCREMENT NOT NULL,
	subjectId INT NOT NULL,
	facultyId INT NOT NULL,
	coefficient FLOAT NOT NULL,
	PRIMARY KEY(id, subjectId),
	FOREIGN KEY(subjectId) REFERENCES subject(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(facultyId) REFERENCES faculty(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE request(
	id INT AUTO_INCREMENT NOT NULL,
	enrolleeId INT NOT NULL,
	facultyId INT NOT NULL,
	averageScore FLOAT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(enrolleeId) REFERENCES enrollee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(facultyId) REFERENCES faculty(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE statement(
	id INT AUTO_INCREMENT NOT NULL,
	facultyId INT NOT NULL,
	date date NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (facultyId) REFERENCES faculty(id)
);

CREATE TABLE statementRequests (
  id INT AUTO_INCREMENT NOT NULL,
  statementId INT NOT NULL,
  requestId INT NOT NULL,
  PRIMARY KEY (id, statementId, requestId),
  FOREIGN KEY (statementId) REFERENCES statement(id),
  FOREIGN KEY (requestId) REFERENCES request(id)
);

INSERT INTO enrollee(firstName, lastName, login, password, email)
VALUES ('Василий','Гончаренко', 'vasbka', '159842a', 'vasilijgoncarenko4@gmail.com'),
	 ('Андрей','Павлюк', 'andrew', 'andrewA', 'odun36@mail.r'),
	  ('Роман','Павлюк', 'romqa', 'romkA', 'odun36@mail.rasdf'),
	  ('Дмитрий','Синельник', 'aztk', 'aztkA', 'odun36@mail.rasdfsds');

INSERT INTO subjecttype(name) VALUES ('Exam'),('Certificate');

INSERT INTO
	subject(name, subjecttypeid)
VALUES
	('Математика',1), ('Математика',2),
	('Украинский язык',1), ('Украинский язык',2),
	('Физика',1), ('Физика',2),
	('Английский язык',1), ('Английский язык',2),
	('История',1), ('История',2);

INSERT INTO
	enrolleeSubject(enrolleeId, subjectId, point)
VALUES
	(1, 1, 160),
	(1, 2, 10),
	(1, 3, 175),
	(1, 4, 7),
	(1, 5, 180),
	(1, 6, 11),
	(2, 1, 180),
	(2, 2, 12),
	(2, 3, 160),
	(2, 4, 10),
	(2, 6, 8),
	(2, 7, 50),
	(3, 1, 195),
	(3, 2, 11),
	(3, 3, 150),
	(3, 4, 10),
	(3, 5, 120);

INSERT INTO faculty(name, budgetCount, generalCount)
VALUES
	('Компьютерные науки', 30, 150),
	('Программная инженерия', 10, 90),
	('Штучный интеллект', 2, 3);

INSERT INTO facultySubject(subjectId, facultyId, coefficient)
VALUES
	(1, 1, 0.3),
	(2, 1, 0.1),
	(3, 1, 0.2),
	(4, 1, 0.1),
	(5, 1, 0.3),
	(1, 2, 0.5),
	(2, 2, 0.3),
	(3, 2, 0.1),
	(4, 2, 0.1),
	(1, 3, 0.4),
	(2, 3, 0.2),
	(3, 3, 0.1),
	(4, 3, 0.1),
	(5, 3, 0.2);

INSERT INTO
	request(enrolleeId, facultyId, averageScore)
VALUES
	(1, 1, 156),
	(1, 2, 140),
	(1, 3, 180);
