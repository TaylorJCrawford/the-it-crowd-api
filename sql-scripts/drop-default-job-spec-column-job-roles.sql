USE TheITCrowd_MiaG;

ALTER TABLE JobRoles ALTER jobSpecUrl DROP DEFAULT;

ALTER TABLE JobRoles MODIFY jobSpecUrl VARCHAR(255) NOT NULL;

-- Update each row with correct job specification link and not point to career lattice
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Software%20Engineer%20(Associate).pdf?csf=1&web=1&e=YPY7uP" WHERE jobId = 1;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Data%20and%20Artificial%20Intelligence/Job%20profile%20-%20Data%20Analyst%20(As).pdf?csf=1&web=1&e=GzyzyI" WHERE jobId = 2;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Shared%20Documents/Product_Capability_Discipline_Specific_Skills_Framework%20v1.0.pdf?csf=1&web=1&e=vBF1Rj" WHERE jobId = 3;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20profile%20-%20Platform%20Engineer%20(Associate).pdf?csf=1&web=1&e=Vt5AUQ" WHERE jobId = 4;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Experience%20Design/Job%20Specifcation%20-%20UX%20Designer%20(Senior%20Assoicate).pdf?csf=1&web=1&e=jQKGki" WHERE jobId = 5;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20profile%20-%20Platform%20Solution%20Architect%20(Manager).pdf?csf=1&web=1&e=Wr9lCA" WHERE jobId = 6;
UPDATE JobRoles SET jobSpecUrl = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20%20Test%20Engineer%20(Associate).pdf?csf=1&web=1&e=P3Pe76" WHERE jobId = 7;
