SET SQL_SAFE_UPDATES = 0;
INSERT IGNORE INTO user (Name, Email, Password, ContactNumber, Address) 
VALUES 
('Akalya NM', 'Akalya@gmail.com', 'Akalya_12', 9276548906, '123 MG Road,Bangalore'),
('Madhu M', 'MadhuM@gmail.com', 'Madhu_123', 9125648906, '123 MG Road,Coimbatore'),
('Abi M', 'Abi082@gmail.com', 'Abi@112', 9765456709, '56 Gandhi Road,Chennai'),
('Priya L', 'Priya@gmail.com', 'priya@lp01', 9061245670, '56 Gandhi Road,Chennai');
INSERT INTO CourierServices (ServiceName, Cost) 
VALUES 
('Standard Shipping', 10.00),
('Express Shipping', 25.00);
INSERT IGNORE INTO Employee (Name, Email, ContactNumber, Role, Salary) 
VALUES 
('Alan', 'alan@courier.com', '9876543211', 'Delivery Agent', 30000),
('Bob', 'Bob@courier.com', '9872113210', 'Delivery Agent', 30000),
('John', 'John@courier.com', '9874532189', 'Delivery Agent', 30000),
('Joyce', 'Joyce@courier.com', '9165890432', 'Delivery Agent', 30000),
('Dev', 'Dev@courier.com', '9076789321', 'Delivery Agent', 30000);
INSERT INTO Location (LocationName, Address) 
VALUES 
('Chennai-Anna Nagar', '123 Main St'),
('Bangalore', '123 MG Road'),
('Coimbatore', '123 MG Road');
DELETE FROM courier WHERE CourierID >= 6;
INSERT INTO courier(SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate) 
VALUES 
('Monisha M', '123 Main St', 'Jack', 'East road-Bangalore', 20, 'Pending', 'TRK12003', '2025-03-20'),
('Akalya NM', '123 MG Road', 'John', 'XYZ St', 30, 'In Transit', 'TRK12346', '2025-03-21'),
('Madhu M', '123 MG Road', 'Mary', 'ABC St', 10, 'Delivered', 'TRK12347', '2025-03-15'),
('Abi M', '56 Gandhi Road', 'Peter', 'DEF St', 20, 'Pending', 'TRK12348', '2025-03-22'),
('Priya L', '56 Gandhi Road', 'Alice', 'GHI St', 40, 'In Transit', 'TRK12349', '2025-03-19');
INSERT INTO Payment (CourierID, LocationID, Amount, PaymentDate) 
VALUES 
(1, 1, 100.00, '2025-03-17'),
(2, 2, 20.00, '2025-03-18'),
(3, 3, 12.50, '2025-03-15'),
(4, 1, 18.00, '2025-03-19'),
(5, 1, 25.00, '2025-03-20');
UPDATE Payment SET Amount = 100 WHERE PaymentID = 1;
UPDATE courier SET DispatchDate = '2025-03-16' WHERE CourierID = 1;
UPDATE courier SET DispatchDate = '2025-03-16' WHERE CourierID = 74;
UPDATE courier SET DispatchDate = '2025-03-18' WHERE CourierID = 75;
UPDATE courier SET DispatchDate = '2025-03-09' WHERE CourierID = 76;
UPDATE courier SET DispatchDate = '2025-03-17' WHERE CourierID = 77;
UPDATE courier SET DispatchDate = '2025-03-11' WHERE CourierID = 78;
UPDATE courier SET EmployeeID = 1 WHERE SenderName = 'Monisha M';
UPDATE courier SET EmployeeID = 2 WHERE SenderName = 'Monisha M';
UPDATE courier SET EmployeeID = 3 WHERE SenderName = 'Akalya NM';
UPDATE courier SET EmployeeID = 4 WHERE SenderName = 'Abi M';
UPDATE courier SET EmployeeID = 5 WHERE SenderName = 'Madhu M';
UPDATE courier SET EmployeeID = 6 WHERE SenderName = 'Priya L';
SET SQL_SAFE_UPDATES = 1;
SELECT * FROM courier;
SELECT SenderName, AVG(DATEDIFF(DeliveryDate, DispatchDate)) AS AvgDeliveryTime FROM Courier GROUP BY SenderName;
SELECT * FROM Courier WHERE Weight BETWEEN 5 AND 20;
SELECT * FROM Employee WHERE Name LIKE '%John%';
SELECT * FROM Payment WHERE Amount > 50;
SELECT SenderName, COUNT(CourierID) AS TotalPackages FROM Courier GROUP BY SenderName;