USE TheITCrowd_MiaG;

CREATE TABLE `Bands` (
	bandId tinyint unsigned,
	bandName VARCHAR(50) NOT NULL,
	PRIMARY KEY (bandId)
);

INSERT INTO Bands (bandId, bandName) VALUES (1,"Leadership Community"), (2,"Principal"), (3,"Manager"), (4,"Consultant"), (5,"Senior Associate"), (6,"Associate"), (7,"Trainee"), (8,"Apprentice");

ALTER TABLE JobRoles
ADD bandId tinyint unsigned;

ALTER TABLE JobRoles
ADD FOREIGN KEY (bandId) REFERENCES Bands(bandId);

UPDATE JobRoles SET bandId = 6 WHERE jobId = 1;
UPDATE JobRoles SET bandId = 7 WHERE jobId = 2;
UPDATE JobRoles SET bandId = 3 WHERE jobId = 3;
UPDATE JobRoles SET bandId = 2 WHERE jobId = 4;
UPDATE JobRoles SET bandId = 6 WHERE jobId = 5;
UPDATE JobRoles SET bandId = 4 WHERE jobId = 6;
UPDATE JobRoles SET bandId = 7 WHERE jobId = 7;

ALTER TABLE JobRoles MODIFY COLUMN bandId tinyint unsigned NOT NULL;

SELECT jobId, job_role_title, band_name 
FROM job_role
LEFT JOIN band USING(band_id);

SELECT jobId, jobName, bandName FROM JobRoles LEFT JOIN Bands USING(bandId);