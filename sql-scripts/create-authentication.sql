USE TheITCrowd_MiaG;

CREATE TABLE AccessRights (
	accessRightId SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	accessRight VARCHAR(20) NOT NULL DEFAULT 'View'
);

INSERT INTO AccessRights (accessRight) VALUES
	('View'), ('Admin'), ('Blocked');

CREATE TABLE Users (
    userId SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    passwordHash TEXT NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    accessRightId SMALLINT UNSIGNED NOT NULL,
	FOREIGN KEY(accessRightId) REFERENCES AccessRights(accessRightId)
);

INSERT INTO Users (email, passwordHash, firstName,
	lastName, accessRightId) VALUES
		('user@users.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
			'Mat', 'Damon', 1),
		('blocked@blockers.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
			'Tom', 'Hanks', 2),
		('admin@admins.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
			'Morgan', 'Freeman', 3);
