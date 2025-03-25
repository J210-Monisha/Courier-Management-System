Create Table Companies (
    Companyid Int Primary Key Auto_Increment,
    Companyname Varchar(255),
    Location Varchar(255)
);

Create Table Jobs (
    Jobid Int Primary Key Auto_Increment,
    Companyid Int,
    Jobtitle Varchar(255) Not Null,
    Jobdescription Text Not Null,
    Joblocation Varchar(255) Not Null,
    Salary Decimal(10,2),
    Jobtype Varchar(50) Not Null,
    Posteddate Datetime Not Null,
    Foreign Key (Companyid) References Companies(Companyid) On Delete Cascade
);

Create Table Applicants (
    Applicant_id Int Primary Key Auto_Increment,
    First_name Varchar(100) Not Null,
    Last_name Varchar(100) Not Null,
    Email Varchar(255) Unique Not Null,
    Phone Varchar(20) Not Null,
    Resume Text Not Null
);

Create Table Applications (
    Applicationid Int Primary Key Auto_Increment,
    Jobid Int,
    Applicantid Int,
    Applicationdate Datetime Not Null,
    Coverletter Text,
    Foreign Key (Jobid) References Jobs(Jobid) On Delete Cascade,
    Foreign Key (Applicantid) References Applicants(Applicant_id) On Delete Cascade
);

Insert Into Companies (Companyname, Location) Values
('Hexaware', 'Chennai'),
('Hi Tech Solutions', 'Bangalore'),
('Xyz Pvt Ltd', 'Cochin');

Insert Into Jobs (Companyid, Jobtitle, Jobdescription, Joblocation, Salary, Jobtype, Posteddate) Values
(1, 'Software Engineer', 'Develop and maintain web applications.', 'Chennai', 120000.00, 'Full-Time', Now()),
(1, 'Data Analyst', 'Analyze large datasets to support business decisions.', 'Chennai', 90000.00, 'Full-Time', Now()),
(2, 'Testing Engineer', 'Develop and execute test cases, automate testing processes, and ensure software quality.', 'Bangalore', 110000.00, 'Full-Time', Now()),
(3, 'Vlsi Engineer', 'Design and verify integrated circuits.', 'Cochin', 85000.00, 'Part-Time', Now());

Insert Into Applicants (First_name, Last_name, Email, Phone, Resume) Values
('Monisha', 'Mohandoss', 'm.monishadoss@gmail.com', '9765478905', 'Experienced software engineer with 5 years of experience.'),
('Brundha', 'Reddy', 'brundha.reddy@gmail.com', '98890643215', 'Data analyst skilled in SQL and Python.'),
('Abhishek', 'Kumar', 'Abhishek34@gmail.com', '9065431867', 'Passionate testing engineer with 2 years of experience.'),
('Rohit', 'Sharma', 'RRohit04@gmail.com', '8765490123', 'Expert in VLSI and Verilog designing.');

Insert Into Applications (Jobid, Applicantid, Applicationdate, Coverletter) Values
(1, 1, Now(), 'I am excited to apply for the Software Engineer position at Hexaware.'),
(2, 2, Now(), 'I would love to bring my analytical skills to the Data Analyst role.'),
(3, 3, Now(), 'I am passionate about analyzing and testing software systems and believe I am a great fit for this role.'),
(4, 4, Now(), 'I am interested in the VLSI Projects and designing by using Verilog.');

INSERT INTO Jobs (Companyid, Jobtitle, Jobdescription, Joblocation, Salary, JobType, Posteddate) 
VALUES 
(2, 'Network Engineer', 'Design, implement, and maintain networking solutions.', 'Bangalore', 95000.00, 'Full-Time', NOW());
INSERT INTO Applicants (First_name, Last_name, Email, Phone, Resume) 
VALUES 
('Amit', 'Sharma', 'amit.sharma@gmail.com', '9876543210', 'Experienced network engineer with 2 years of experience.');
INSERT INTO Applications (Jobid, Applicantid, Applicationdate, Coverletter) 
VALUES 
(5, 5, NOW(), 'I am excited to apply for the Network Engineer position at Hi Tech Solutions.');

Select * From Companies;
Select * From Jobs;
Select * From Applicants;
Select * From Applications;

select j.jobtitle,count(a.applicationid) as Applicationcount
from jobs j 
left join Applications a on j.jobid=a.jobid
group by j.jobtitle;

INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) 
VALUES 
(2, 'Network Engineer', 'Design, implement, and maintain networking solutions.', 'Pune', 95000.00, 'Full-time', NOW());

select j.jobtitle,c.companyname,c.location,j.salary
from jobs j
join companies c on j.companyid=c.companyid
 where j.salary between 110000 and 120000
group by j.jobtitle,c.companyname,c.location,j.salary;

select j.JobTitle, c.CompanyName, a.ApplicationDate  
from Applications a  
join Jobs j ON a.JobID = j.JobID  
join Companies c ON j.CompanyID = c.CompanyID  
where a.ApplicantID = 2;

select AVG(Salary) as AverageSalary from Jobs where Salary > 0;

select c.CompanyName, COUNT(j.JobID) AS JobCount
from Companies c
join Jobs j on c.CompanyID = j.CompanyID
group by c.CompanyID
having COUNT(j.JobID) = (
    Select MAX(JobCount)
    from (
        select COUNT(JobID) AS JobCount
        from Jobs
        group by CompanyID
    ) as JobCounts
);

select j.joblocation,a.applicantid 
from jobs j
join Applications a on j.jobid=a.jobid
where j.joblocation='Chennai' ;

Select a.First_name, a.Last_name, c.Companyname
From Applicants a
Join Applications ap On a.Applicant_id = ap.Applicantid
Join Jobs j On ap.Jobid = j.Jobid
Join Companies c On j.Companyid = c.Companyid
Where c.Location = 'chennai' And a.Resume Like '%3 years%';

select distinct(jobtitle) ,salary from jobs where salary between 60000 and 85000;

Select j.jobid, j.jobtitle, j.companyid, c.companyname, j.salary, j.joblocation 
From jobs j
Left join applications a On j.jobid = a.jobid
Left join companies c On j.companyid = c.companyid
Where a.applicationid Is null;

select c.companyname, count(j.jobid) as job_count
from companies c
left join jobs j on c.companyid = j.companyid
group by c.companyname;

Select a.first_name, a.last_name, c.companyname, j.jobtitle 
From applications app
Join applicants a On app.applicantid = a.applicant_id
Join jobs j On app.jobid = j.jobid
Join companies c On j.companyid = c.companyid;

select a.first_name, a.last_name, coalesce(c.companyname, 'No application') as companyname, coalesce(j.jobtitle, 'No application') as jobtitle
from applicants a
left join applications app on a.applicant_id = app.applicantid
left join jobs j on app.jobid = j.jobid
left join companies c on j.companyid = c.companyid
union
select null as first_name, null as last_name, c.companyname, j.jobtitle
from jobs j
left join companies c on j.companyid = c.companyid
left join applications app on j.jobid = app.jobid
where app.jobid is null; 

Select c.companyname, j.jobtitle, j.salary
From jobs j
Join companies c On j.companyid = c.companyid
Where j.salary > (Select Avg(salary) From jobs);

alter table  applicants 
add column city varchar(100),
add column state varchar(100);

SET SQL_SAFE_UPDATES = 0;

update applicants 
set city = 'Chennai', state = 'Tamil Nadu' 
where first_name = 'Monisha' and last_name = 'Mohandoss';

update applicants 
set city = 'Bangalore', state = 'Karnataka' 
where first_name = 'Brundha' and last_name = 'Reddy';

update applicants 
set city = 'Cochin', state = 'Kerala' 
where first_name = 'Abhishek' and last_name = 'Kumar';

update applicants 
set city = 'Hyderabad', state = 'Telangana' 
where first_name = 'Rohit' and last_name = 'Sharma';

select first_name, last_name, CONCAT(city, ', ', state) as location 
from applicants;

Select jobtitle, salary, joblocation
From jobs
Where jobtitle Like '%Developer%' Or jobtitle Like '%Engineer%';

select a.first_name, a.last_name, coalesce(j.jobtitle, 'No job applied') as jobtitle
from applicants a
left join applications app on a.applicant_id = app.applicantid
left join jobs j on app.jobid = j.jobid
union
select null as first_name, null as last_name, j.jobtitle 
from jobs j
left join applications app on j.jobid = app.jobid
where app.jobid is null; 

Select a.first_name, a.last_name, c.companyname, c.location
From applicants a
Join applications app On a.applicant_id = app.applicantid
Join jobs j On app.jobid = j.jobid
Join companies c On j.companyid = c.companyid
Where c.location = 'Chennai' And a.resume Like '%3 years%' Or a.resume Like '%4 years%' Or a.resume Like '%5 years%';