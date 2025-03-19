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
(1, 1, 100.0,'2025-03-20'),
(2, 2, 20000, '2025-03-18'),
(3, 3, 12500, '2025-03-15'),
(4, 1, 1800, '2025-03-19'),
(5, 1, 25.0, '2025-03-20');
SELECT*FROM Payment;
UPDATE Payment SET Amount = 100 WHERE PaymentID = 1;
UPDATE Payment SET Amount = 1000 WHERE PaymentID = 2;
UPDATE Payment SET Amount = 200 WHERE PaymentID = 3;
UPDATE Payment SET Amount = 1500 WHERE PaymentID = 4;
UPDATE Payment SET Amount = 500 WHERE PaymentID = 5;
DELETE FROM Payment WHERE PaymentID > 15;
UPDATE courier SET DispatchDate = '2025-03-16' WHERE CourierID = 1;
UPDATE courier SET DispatchDate = '2025-03-16' WHERE CourierID = 2;
UPDATE courier SET DispatchDate = '2025-03-18' WHERE CourierID = 3;
UPDATE courier SET DispatchDate = '2025-03-09' WHERE CourierID = 4;
UPDATE courier SET DispatchDate = '2025-03-17' WHERE CourierID = 5;
ALTER TABLE Courier ADD COLUMN EmployeeID INT;
UPDATE courier SET EmployeeID = 1 WHERE SenderName = 'Monisha M' AND CourierID = 1;
UPDATE courier SET EmployeeID = 2 WHERE SenderName = 'Monisha M' AND CourierID = 2;
UPDATE courier SET EmployeeID = 3 WHERE SenderName = 'Akalya NM'; 
UPDATE courier SET EmployeeID = 3 WHERE SenderName = 'Madhu M'; 
UPDATE courier SET EmployeeID = 4 WHERE SenderName = 'Abi M';
UPDATE courier SET EmployeeID = 5 WHERE SenderName = 'Priya L';
SET SQL_SAFE_UPDATES = 1;
SELECT * FROM courier;
SELECT SenderName, AVG(DATEDIFF(DeliveryDate, DispatchDate)) AS AvgDeliveryTime 
FROM Courier 
GROUP BY SenderName;
SELECT * FROM Courier WHERE Weight BETWEEN 5 AND 20;
SELECT * FROM Employee WHERE Name LIKE '%John%';
SELECT * FROM Payment WHERE Amount > 50;
SELECT EmployeeID, COUNT(CourierID) AS TotalCouriers
FROM Courier
WHERE EmployeeID IS NOT NULL
GROUP BY EmployeeID;
SELECT LocationID, SUM(Amount) AS TotalRevenue
FROM Payment
GROUP BY LocationID;
SELECT LocationID, COUNT(CourierID) AS TotalDelivered
FROM Payment
GROUP BY LocationID;
SELECT SenderName, AVG(DATEDIFF(DeliveryDate, DispatchDate)) AS AvgDeliveryTime
FROM Courier
GROUP BY SenderName
ORDER BY AvgDeliveryTime DESC
LIMIT 1;
SELECT LocationID, SUM(Amount) AS TotalPayments
FROM Payment
GROUP BY LocationID
HAVING TotalPayments < 10;
SELECT LocationID, SUM(Amount) AS TotalPayments
FROM Payment
GROUP BY LocationID;
SELECT CourierID, SUM(Amount) AS TotalPayments
FROM Payment
WHERE LocationID = 1
GROUP BY CourierID
HAVING TotalPayments > 1000;
SELECT LocationID, SUM(Amount) AS TotalAmount
FROM Payment
WHERE PaymentDate < '2025-03-08'
GROUP BY LocationID
HAVING TotalAmount > 500;
SELECT * FROM Location;
DELETE FROM Location WHERE LocationID > 5;