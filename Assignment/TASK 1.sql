CREATE TABLE USER (
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
    Status VARCHAR(50),
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
    PaymentDate DATE NOT NULL
    );