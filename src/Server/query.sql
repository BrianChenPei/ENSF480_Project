Use SENG401;
-- drop table staff;
-- CREATE TABLE `staff` (
--   `idstaff` int NOT NULL,
--   `username` varchar(45) NOT NULL,
--   `password` varchar(45) NOT NULL,
--   `Name` varchar(45) NOT NULL,
--   `Phone`  varchar(45) NOT NULL,
--   `Address` varchar(45) NOT NULL,
--   `ContactNumber` varchar(45) NOT NULL,
--   `staffType` varchar(45) NOT NULL,
--   `clearanceLevel` varchar(45) NOT NULL,
--   PRIMARY KEY (`idstaff`),
--   UNIQUE KEY `idstaff_UNIQUE` (`idstaff`)
-- );

INSERT INTO staff ( idstaff, username, password, Name, Phone, Address, ContactNumber, staffType, clearanceLevel)
VALUES
( -- first row: values for the columns in the list above
 1001,"jdoe", "12345", "John Doe", "403-555 2212", "123 maple st SW", "587-999 0202, D, 4"
),
( -- second row: values for the columns in the list above
  1002,"mjane", "abcde", "Mary Jane", "403-555 2212", "123 oak st SW", "587-999 0202, N, 2"
),
( -- second row: values for the columns in the list above
  1003,"jdoe", "098765", "John Doe", "403-555 2212", "123 birch st SW", "587-999 0202, D, 3"
),
( -- second row: values for the columns in the list above
  1004,"admin", "asceplius", "Mike ", "403-555 2212", "123 starwars st SW", "587-999 0202, A, 5"
)