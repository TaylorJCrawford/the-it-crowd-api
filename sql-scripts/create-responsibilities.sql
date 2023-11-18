USE TheITCrowd_MiaG;

CREATE TABLE Responsibilities (
    responsibilityId SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    responsibilityTextBody TEXT NOT NULL,
    responsibilityTextPoints TEXT NULL,
    jobId INT NOT NULL,
    FOREIGN KEY(jobId) REFERENCES JobRoles(jobId)
);

-- Software Engineer (Trainee) Responsibilities:
SET @ds0 := "As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to ";
SET @ds1 := 'people’s lives – the lives of people you know After taking part in our award-winning, seven-week Engineering Academy, ';
SET @ds2 := 'you will then join one of our many project teams, to learn from our experienced developers, project managers and ';
SET @ds3 := 'customer-facing staff You’ll have great support and mentoring, balanced with the experience of being given real, ';
SET @ds4 := 'meaningful work to do, to help you truly develop both technically and professionally';

SET @ps0 := '^ Contribute to developing high quality solutions which impact the lives of users worldwide ';
SET @ps1 := '^ You’ll work as part of a team to solve problems and produce innovative software solutions ';
SET @ps2 := '^ Learn about new technologies and approaches, with talented colleagues who will help you learn, develop and grow ';
SET @ps3 := '^ Based in our Kainos office and often on our customer sites, you will work on a project teams to learn how to develop and ';
SET @ps31 := 'unit test developing and unit testing straightforward or low complexity components, and then moving on to more complex elements as you increase your knowledge ';
SET @ps4 := '^ Work with other developers in working through designs and user stories and to produce real development solutions ';
SET @ps5 := '^ Will be fully supported by experienced colleagues in the team to follow designs, and then progress to assist in any other aspect ';
SET @ps51 := 'of the project life-cycle under supervision ';
SET @ps6 := '^ Develop excellent technical, team-working and Agile project experience';

INSERT INTO Responsibilities (responsibilityTextBody, responsibilityTextPoints, jobId) VALUES
    (CONCAT(@ds0, @ds1, @ds3, @ds4), CONCAT(@ps0, @ps1, @ps2, @ps3, @ps31, @ps4, @ps5, @ps51, @ps6), 1);

-- Technical Architect (Consultant)
INSERT INTO JobSpecs(jobSpecName, jobSpecSharepointLink) VALUES
	('Technical Architect Consultant', 'https://eeee.com');

INSERT INTO JobRoles(jobName, jobCapabilityId, jobSpecId) VALUES
	('Technical Architect', 2, 12); # ID May Need Update Depending on Database State.

SET @da0 := 'As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions ';
SET @da1 := 'which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects ';
SET @da2 := 'to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach ';
SET @da3 := 'and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced ';
SET @da4 := 'environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, ';
SET @da5 := 'with room to learn, develop and grow.';

INSERT INTO Responsibilities (responsibilityTextBody, responsibilityTextPoints, jobId) VALUES
    (CONCAT(@da0, @da1, @da2, @da3, @da4, @da5), NULL, 6); # ID May Need Update Depending on Database State.

-- Test Engineer (Trainee)
INSERT INTO JobSpecs(jobSpecName, jobSpecSharepointLink) VALUES
	('Test Engineer Trainee', 'https://eeee.com');

INSERT INTO JobRoles(jobName, jobCapabilityId, jobSpecId) VALUES
	('Test Engineer', 2, 13); # ID May Need Update Depending on Database State.

SET @dt0 := 'As a Test Engineer (Trainee) in Kainos, you’ll work within a multi-skilled agile team, developing and executing functional ';
SET @dt1 := 'automated and manual tests to help the team deliver working application software that meets user needs You’ll do this whilst learning about new technologies ';
SET @dt2 := 'and approaches, with talented colleagues who will help you learn, develop and grow language and has a desire to develop their technical/test automation skills ';
SET @dt3 := 'furtherg Seleniumg Non-functional – Performance, Load, accessibility etcWHO YOU ARE Our vision is to enable outstanding people to create digital solutions that ';
SET @dt4 := "have a positive impact on people’s lives Our values aren't abstract; they are the behaviours we expect from each other every day and underpin everything that we do ";
SET @dt5 := 'We expect everyone to display our values by being determined in how obstacles are overcome; honest when dealing with others; respectful of how you treat others; creative ';
SET @dt6 := 'to find solutions to complex problems and cooperative by sharing information, knowledge and experience';

INSERT INTO Responsibilities (responsibilityTextBody, responsibilityTextPoints, jobId) VALUES
    (CONCAT(@dt0, @dt1, @dt2, @dt3, @dt4, @dt5, @dt6), NULL, 7); # ID May Need Update Depending on Database State.
