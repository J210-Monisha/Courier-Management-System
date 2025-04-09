CREATE TABLE Companies (
    CompanyID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyName VARCHAR(255) NOT NULL,
    Location VARCHAR(255) NOT NULL
);

CREATE TABLE JobListings (
    JobID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyID INT,
    JobTitle VARCHAR(255) NOT NULL,
    JobDescription TEXT NOT NULL,
    JobLocation VARCHAR(255) NOT NULL,
    Salary DECIMAL(10,2),
    JobType VARCHAR(50) NOT NULL,
    PostedDate DATETIME NOT NULL,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID) ON DELETE CASCADE
);

CREATE TABLE Applicant (
    ApplicantID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Phone VARCHAR(20) NOT NULL,
    Resume TEXT NOT NULL
);

CREATE TABLE JobApplication (
    ApplicationID INT PRIMARY KEY AUTO_INCREMENT,
    JobID INT,
    ApplicantID INT,
    ApplicationDate DATETIME NOT NULL,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES JobListings(JobID) ON DELETE CASCADE,
    FOREIGN KEY (ApplicantID) REFERENCES Applicant(ApplicantID) ON DELETE CASCADE
);
show tables;
INSERT INTO Companies (CompanyName, Location)
VALUES ('OpenAI', 'San Francisco'),
       ('Google', 'Bangalore'),
       ('Microsoft', 'Chennai');
INSERT INTO JobListings (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate)
VALUES 
(1, 'AI Researcher', 'Work on cutting-edge AI models.', 'Remote', 150000.00, 'Full-Time', NOW()),
(2, 'Software Engineer', 'Backend systems developer.', 'California', 120000.00, 'Full-Time', NOW()),
(3, 'Data Analyst', 'Analyze big data.', 'New York', 90000.00, 'Part-Time', NOW());

INSERT INTO JobApplication (JobID, ApplicantID, ApplicationDate, CoverLetter)
VALUES (1, 1, NOW(), 'I am passionate about AI and would love to contribute to your mission.');

delete from JobApplication where ApplicationID=2;

INSERT INTO Applicant (FirstName, LastName, Email, Phone, Resume)
VALUES 
('Monisha', 'Mohandoss', 'mmonisha@gmail.com', '9876543210', 'bob_resume.pdf'),
('Sarah', 'Williams', 'sarah.williams@gmail.com', '9123456780', 'clara_resume.pdf'),
('David', 'Brown', 'david.brown@gmail.com', '9988776655', 'david_resume.pdf'),
('Eva', 'Taylor', 'eva.taylor@gmail.com', '8899776655', 'eva_resume.pdf');

INSERT INTO JobApplication (JobID, ApplicantID, ApplicationDate, CoverLetter)
VALUES 
(2, 2, NOW(), 'Excited to work on backend systems at Google.'),
(3, 3, NOW(), 'I have strong experience in data analysis and statistics.'),
(1, 4, NOW(), 'AI is my passion. Looking forward to contributing at OpenAI.'),
(2, 5, NOW(), 'Backend development aligns with my skills and interests.');


select* from applicant;
select * from companies;
select * from jobapplication;
select * from joblistings;
delete from applicant where ApplicantID=6;