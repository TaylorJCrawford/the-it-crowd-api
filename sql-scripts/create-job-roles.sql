USE TheITCrowd_MiaG;

CREATE TABLE JobRoles (
    jobId INT AUTO_INCREMENT PRIMARY KEY,
    jobName VARCHAR(255) NOT NULL
);

INSERT INTO JobRoles (jobName) VALUES
    ('Software Engineer'),
    ('Data Analyst'),
    ('Project Manager'),
    ('Network Administrator'),
    ('Graphic Designer');
    