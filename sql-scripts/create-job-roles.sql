USE TheITCrowd_MiaG;

CREATE TABLE JobRoles (
    jobId INT AUTO_INCREMENT PRIMARY KEY,
    jobName VARCHAR(255) NOT NULL,
    jobSpecUrl VARCHAR(255) NOT NULL,
);

INSERT INTO JobRoles (jobName, jobSpecUrl) VALUES
    ('Software Engineer', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Software%20Engineer%20(Associate).pdf?csf=1&web=1&e=YPY7uP"),
    ('Data Analyst', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Data%20and%20Artificial%20Intelligence/Job%20profile%20-%20Data%20Analyst%20(As).pdf?csf=1&web=1&e=GzyzyI"),
    ('Data Architect', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Data%20and%20Artificial%20Intelligence/Job%20profile%20-%20Data%20Architect%20(M).pdf?csf=1&web=1&e=8kGbsx"),
    ('Platform Engineer', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20profile%20-%20Platform%20Engineer%20(Associate).pdf?csf=1&web=1&e=Vt5AUQ"),
    ('UX Designer', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20%20Test%20Engineer%20(Associate).pdf?csf=1&web=1&e=P3Pe76");
    ('Platform Solution Architect', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20profile%20-%20Platform%20Solution%20Architect%20(Manager).pdf?csf=1&web=1&e=Wr9lCA");
    ('Test Engineer', "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20%20Test%20Engineer%20(Associate).pdf?csf=1&web=1&e=P3Pe76");

UPDATE TheITCrowd_MiaG.JobRoles SET bandId=3 WHERE jobId=6;

UPDATE JobRoles SET bandId = 6 WHERE jobId = 2;