SET FOREIGN_KEY_CHECKS=0;
SET CHARSET 'utf8';
DROP TABLE IF EXISTS  enrollee, subjectType, subject,
faculty, facultySubjectType,request, statement, facultysubject, enrolleeSubject, statementRequests;
 SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE enrollee(
	enrolleeId INT AUTO_INCREMENT NOT NULL,
	enrolleeFirstName VARCHAR(15) NOT NULL,
	enrolleeLastName VARCHAR(15) NOT NULL,
	enrolleeLogin VARCHAR(15) NOT NULL,
	enrolleePassword VARCHAR(20) NOT NULL,
	enrolleeEmail VARCHAR(50) NOT NULL,
	PRIMARY KEY(enrolleeId),
	UNIQUE(enrolleeEmail),
	UNIQUE(enrolleeLogin)
);

CREATE TABLE subjectType(
	subjectTypeId INT AUTO_INCREMENT NOT NULL,
	subjectTypeName VARCHAR(15) NOT NULL,
	PRIMARY KEY(subjectTypeId)
);

CREATE TABLE subject(
	subjectId INT AUTO_INCREMENT NOT NULL,
	subjectName VARCHAR(50) NOT NULL,
	subjectSubjectTypeId INT NOT NULL,
	PRIMARY KEY(subjectId, subjectSubjectTypeId),
	FOREIGN KEY(subjectSubjectTypeId) REFERENCES subjectType(subjectTypeId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE enrolleeSubject(
	enrolleeSubjectId INT AUTO_INCREMENT NOT NULL,
	enrolleeSubjectEnrolleeId INT NOT NULL,
	enrolleeSubjectSubjectId INT NOT NULL,
	enrolleeSubjectPoint FLOAT NOT NULL,
	PRIMARY KEY(enrolleeSubjectId, enrolleeSubjectEnrolleeId, enrolleeSubjectSubjectId),
	FOREIGN KEY(enrolleeSubjectEnrolleeId) REFERENCES enrollee(enrolleeId) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(enrolleeSubjectSubjectId) REFERENCES subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE faculty(
	facultyId INT AUTO_INCREMENT NOT NULL,
	facultyName VARCHAR(50) NOT NULL,
	facultyGeneralCount INT NOT NULL,
	facultyBudgetCount INT NOT NULL,
	PRIMARY KEY(facultyId)
);

CREATE TABLE facultySubject(
	facultySubjectId INT AUTO_INCREMENT NOT NULL,
	facultySubjectSubjectId INT NOT NULL,
	facultySubjectFacultyId INT NOT NULL,
	facultySubjectCoefficient FLOAT NOT NULL,
	PRIMARY KEY(facultySubjectId, facultySubjectSubjectId),
	FOREIGN KEY(facultySubjectSubjectId) REFERENCES subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(facultySubjectFacultyId) REFERENCES faculty(facultyId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE request(
	requestId INT AUTO_INCREMENT NOT NULL,
	requestEnrolleeId INT NOT NULL,
	requestFacultyId INT NOT NULL,
	requestAverageScore FLOAT NOT NULL,
	PRIMARY KEY(requestId),
	FOREIGN KEY(requestEnrolleeId) REFERENCES enrollee(enrolleeId) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(requestFacultyId) REFERENCES faculty(facultyId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE statement(
	statementId INT AUTO_INCREMENT NOT NULL,
	statementFacultyId INT NOT NULL,
	date date NOT NULL,
	PRIMARY KEY(statementId),
	FOREIGN KEY (statementFacultyId) REFERENCES faculty(facultyId)
);

CREATE TABLE statementRequests (
  statementRequestsId INT AUTO_INCREMENT NOT NULL,
  statementRequestsStatementId INT NOT NULL,
  statementRequestsRequestId INT NOT NULL,
  PRIMARY KEY (statementRequestsId, statementRequestsStatementId, statementRequestsRequestId),
  FOREIGN KEY (statementRequestsStatementId) REFERENCES statement(statementId),
  FOREIGN KEY (statementRequestsRequestId) REFERENCES request(requestId)
);

INSERT INTO enrollee(enrolleeFirstName, enrolleeLastName, enrolleeLogin, enrolleePassword, enrolleeEmail)
VALUES ('Василий','Гончаренко', 'vasbka', '159842a', 'vasilijgoncarenko4@gmail.com'),
	 ('Андрей','Павлюк', 'andrew', 'andrewA', 'odun36@mail.r'),
	  ('Роман','Павлюк', 'romqa', 'romkA', 'odun36@mail.rasdf'),
	  ('Дмитрий','Синельник', 'aztk', 'aztkA', 'odun36@mail.rasdfsds');

INSERT INTO subjecttype(subjecttypeName) VALUES ('Exam'),('Certificate');

INSERT INTO
	subject(subjectname, subjectsubjecttypeid)
VALUES
	('Математика',1), ('Математика',2),
	('Украинский язык',1), ('Украинский язык',2),
	('Физика',1), ('Физика',2),
	('Английский язык',1), ('Английский язык',2),
	('История',1), ('История',2);

INSERT INTO
	enrolleeSubject(enrolleeSubjectenrolleeId, enrolleeSubjectsubjectId, enrolleeSubjectpoint)
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

INSERT INTO faculty(facultyname, facultybudgetCount, facultygeneralCount)
VALUES
	('Компьютерные науки', 30, 150),
	('Программная инженерия', 10, 90),
	('Штучный интеллект', 2, 3);

INSERT INTO facultySubject(facultySubjectsubjectId, facultySubjectfacultyId, facultySubjectcoefficient)
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
	request(requestenrolleeId, requestfacultyId, requestaverageScore)
VALUES
	(1, 1, 156),
	(1, 2, 140),
	(1, 3, 180);
