USE CourierDB;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Courier;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS CourierServices;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Location;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE User (
    UserID INT PRIMARY KEY auto_increment,
    Name VARCHAR(255) ,
    Email VARCHAR(255) UNIQUE,
    Password VARCHAR(255) ,
    ContactNumber VARCHAR(20),
    Address TEXT
);
CREATE TABLE Courier (
    CourierID INT PRIMARY KEY AUTO_INCREMENT,
    SenderName VARCHAR(255) NOT NULL,
    SenderAddress TEXT NOT NULL,
    ReceiverName VARCHAR(255) NOT NULL,
    ReceiverAddress TEXT NOT NULL,
    Weight DECIMAL(5,2) NOT NULL,
    Status VARCHAR(50) CHECK (Status IN ('Pending', 'In Transit', 'Delivered')),
    TrackingNumber VARCHAR(20) UNIQUE NOT NULL,
    DeliveryDate DATE  
);
CREATE TABLE CourierServices (
    ServiceID INT PRIMARY KEY AUTO_INCREMENT,
    ServiceName VARCHAR(100) NOT NULL,
    Cost DECIMAL(8,2) NOT NULL
);
CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    ContactNumber VARCHAR(20),
    Role VARCHAR(50),
    Salary DECIMAL(10,2)
);
CREATE TABLE Location (
    LocationID INT PRIMARY KEY AUTO_INCREMENT,
    LocationName VARCHAR(100) NOT NULL,
    Address TEXT NOT NULL
);
CREATE TABLE Payment (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    CourierID INT,
    LocationID INT,
    Amount DECIMAL(10,2) NOT NULL,
    PaymentDate DATE NOT NULL,
    FOREIGN KEY (CourierID) REFERENCES Courier(CourierID) ON DELETE CASCADE,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID) ON DELETE SET NULL
);
INSERT INTO User (Name, Email, Password, ContactNumber, Address) 
VALUES 
('Monisha M', 'Monisha@gmail.com', 'Monisha@123', '9876543210', '123 Main St,Chennai'),
('Akalya NM', 'Akalya@gmail.com', 'Akalya_12', '9276548906', '123 MG Road,Bangalore'),
('Madhu M', 'MadhuM@gmail.com', 'Madhumitha@gmail.com', '9125648906', '123 MG Road,Coimbatore'),
('Abi M', 'Abi082@gmail.com', 'Abi@112', '9765456709', '56 Gandhi Road,Chennai'),
('Priya L', 'Priya@gmail.com', 'priya@lp01', '9061245670', '56 Gandhi Road,Chennai');
INSERT INTO CourierServices (ServiceName, Cost)
VALUES 
('Standard Shipping', 10.00),
('Express Shipping', 25.00);
INSERT INTO Employee (Name, Email, ContactNumber, Role, Salary)
VALUES 
('Alan', 'alan@courier.com', '9876543211', 'Delivery Agent', 30000),
('Bob', 'Bob@courier.com', '9872113210', 'Delivery Agent', 30000),
('John', 'John@courier.com', '9874532189', 'Delivery Agent', 30000),
('Joyce', 'Joyce@courier.com', '9165890432', 'Delivery Agent', 30000),
('Dev', 'Dev@courier.com', '9076789321', 'Delivery Agent', 30000);
INSERT INTO Location (LocationName, Address)
VALUES 
('Chennai-Anna Nagar','123 Main St'),
('Bangalore','123 MG Road'),
('Coimbatore','123 MG Road'),
('Chennai-Anna Nagar','56 Gandhi Road'),
('Chennai-Anna Nagar','56 Gandhi Road');
INSERT INTO Courier (SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate)
VALUES 
('Monisha M', '123 Main St', 'Moni', '7th St', 50, 'Pending', 'TRK12345', '2025-03-20'),
('Akalya NM', '123 MG Road', 'John', 'XYZ St', 30, 'In Transit', 'TRK12346', '2025-03-21'),
('Madhu M', '123 MG Road', 'Mary', 'ABC St', 10, 'Delivered', 'TRK12347', '2025-03-15'),
('Abi M', '56 Gandhi Road', 'Peter', 'DEF St', 20, 'Pending', 'TRK12348', '2025-03-22'),
('Priya L', '56 Gandhi Road', 'Alice', 'GHI St', 40, 'In Transit', 'TRK12349', '2025-03-19');
INSERT INTO Payment (CourierID, LocationID, Amount, PaymentDate)
VALUES 
(1, 1, 15.00, '2025-03-17'),
(2, 2, 20.00, '2025-03-18'),
(3, 3, 12.50, '2025-03-15'),
(4, 1, 18.00, '2025-03-19'),
(5, 1, 25.00, '2025-03-20');
SELECT * FROM User;
SELECT * FROM Courier;
SELECT * FROM Payment;