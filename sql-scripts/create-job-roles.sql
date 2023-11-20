USE TheITCrowd_MiaG;

CREATE TABLE JobRoles (
    jobId INT AUTO_INCREMENT PRIMARY KEY,
    jobName VARCHAR(255) NOT NULL
);

INSERT INTO JobRoles (jobName) VALUES
    ('Software Engineer', 'https://kainossoftwareltd.sharepoint.com/people/SitePages/Career-Lattice.aspx'),
    ('Data Analyst', 'https://kainossoftwareltd.sharepoint.com/people/SitePages/Career-Lattice.aspx'),
    ('Project Manager', 'https://kainossoftwareltd.sharepoint.com/people/SitePages/Career-Lattice.aspx'),
    ('Network Administrator', 'https://kainossoftwareltd.sharepoint.com/people/SitePages/Career-Lattice.aspx'),
    ('Graphic Designer', 'https://kainossoftwareltd.sharepoint.com/people/SitePages/Career-Lattice.aspx');
    