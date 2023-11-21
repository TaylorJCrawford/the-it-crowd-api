USE TheITCrowd_MiaG;

CREATE TABLE `Responsibilities` (
	responsibilityId smallint unsigned AUTO_INCREMENT,
	responsibilityDetails TEXT NOT NULL,
	jobId int NOT NULL,
	PRIMARY KEY (responsibilityId),
	FOREIGN KEY (jobId) REFERENCES JobRoles(jobId)
);

INSERT INTO Responsibilities (responsibilityDetails, jobId) VALUES
("Experience of building and testing modern software applications", 1),
("Experience of applying common design principles and patterns", 1),
("Experience of working in a collaborative team environment", 1),
("You’re flexible and overcome obstacles to get the job done to achieve personal,
team, and business goals.", 1),
("You actively look for better ways to do things using your imagination to find fresh
solutions to complex problems.", 1),
("You are always constructive when giving or receiving feedback, being transparent
and truthful when dealing with others.", 1);