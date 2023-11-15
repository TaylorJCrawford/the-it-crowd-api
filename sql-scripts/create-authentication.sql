USE TheITCrowd_MiaG;

CREATE TABLE Users (
    userId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    passwordHash TEXT NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    accessRight ENUM('Dummy', 'Blocked', 'View', 'Admin') DEFAULT 'View' NOT NULL,
);

DROP PROCEDURE IF EXISTS insertFakeUserData;
DELIMITER $$
	CREATE PROCEDURE insertFakeUserData()
		BEGIN
			START TRANSACTION;

				INSERT INTO Users (email, passwordHash, passwordSalt, firstName,
					lastName, accessRight) VALUES
						('user@users.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
						    'Mat', 'Damon', 'View'),
						('blocked@blockers.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
						    'Tom', 'Hanks', 'Blocked'),
						('admin@admins.com', '$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y',
							'Morgan', 'Freeman', 'Admin'));
                GET DIAGNOSTICS @rows = ROW_COUNT;
					IF @rows != 3 THEN
						ROLLBACK;
						SELECT 'Transaction (insertFakeUserData) rolled back due to error: ' + @rows;
					ELSE
						COMMIT;
						SELECT 'Transaction (insertFakeUserData) committed successfully';
				END IF;
		END $$
DELIMITER ;

CALL insertFakeUserData();
