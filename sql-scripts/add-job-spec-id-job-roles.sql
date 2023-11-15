-- add job spec id column to job roles
ALTER TABLE JobRoles ADD COLUMN jobSpecId INT NOT NULL;

-- make this column a foreign key
ALTER TABLE JobRoles  ADD FOREIGN KEY (jobSpecId) REFERENCES JobSpecs (jobSpecId);

-- insert test data into the table
INSERT INTO JobRoles (jobName, jobCapabilityId, jobSpecId) VALUES ("Software Engineer", 1,1);
INSERT INTO JobRoles (jobName, jobCapabilityId, jobSpecId) VALUES ("Test Engineer", 1,2);

INSERT INTO JobRoles (jobName,jobCapabilityId, jobSpecId ) VALUES
    ('Software Engineer',2, 4),
    ('Data Analyst',2, 7),
    ('Project Manager',7, 8),
    ('Network Administrator',1, 10),
    ('Graphic Designer',6, 11);