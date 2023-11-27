USE TheITCrowd_MiaG;

CREATE TABLE JobCapabilities(
    jobCapabilitiesId INT AUTO_INCREMENT,
    jobCapabilitiesName VARCHAR(255),
    PRIMARY KEY (jobCapabilitiesId)
);

INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Engineering');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Platforms');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Data and Artificial Intelligence');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Cyber Security');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Workday');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Experience Design');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Product');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Delivery');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Operations');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Business Development and Marketing');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Organisational Strategy and Planning');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('People');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Commercial and Financial Management');
INSERT INTO JobCapabilities (jobCapabilitiesName) VALUES ('Business Services Support');

ALTER TABLE JobRoles
ADD COLUMN jobCapabilityId INT;

ALTER TABLE JobRoles
ADD FOREIGN KEY (jobCapabilityId) REFERENCES JobCapabilities(jobCapabilitiesId);


INSERT INTO JobRoles (jobName,jobCapabilityId ) VALUES
    ('Software Engineer',2),
    ('Data Analyst',2),
    ('Project Manager',7),
    ('Network Administrator',1),
    ('Graphic Designer',6);