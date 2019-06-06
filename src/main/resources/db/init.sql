SET FOREIGN_KEY_CHECKS=0;
SET CHARSET 'utf8';
DROP TABLE IF EXISTS  enrollee, subjectType, subject,
faculty, facultySubjectType,request, statement, facultysubject, enrolleeSubject, statementRequests;
 SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE enrollee(
	enrolleeId INT AUTO_INCREMENT NOT NULL,
	enrolleeFirstName VARCHAR(100) NOT NULL,
	enrolleeLastName VARCHAR(100) NOT NULL,
	enrolleeLogin VARCHAR(400) NOT NULL,
	enrolleePassword VARCHAR(100) NOT NULL,
	enrolleeEmail VARCHAR(400) NOT NULL,
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

INSERT INTO subjecttype(subjecttypeName) VALUES ('Exam'),('Certificate');
