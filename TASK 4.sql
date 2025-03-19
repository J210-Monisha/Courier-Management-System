SELECT p.*, c.SenderName, c.ReceiverName, c.Weight, c.Status 
FROM Payment p 
INNER JOIN Courier c ON p.CourierID = c.CourierID;

SELECT p.*, l.LocationName, l.Address 
FROM Payment p 
INNER JOIN Location l ON p.LocationID = l.LocationID;

SELECT p.*, c.SenderName, c.ReceiverName, c.Weight, c.Status, l.LocationName, l.Address 
FROM Payment p 
INNER JOIN Courier c ON p.CourierID = c.CourierID 
INNER JOIN Location l ON p.LocationID = l.LocationID;

SELECT p.*, c.*
FROM Payment p 
INNER JOIN Courier c ON p.CourierID = c.CourierID;

SELECT CourierID, SUM(Amount) AS TotalPayments
FROM Payment
GROUP BY CourierID;

SELECT * FROM Payment WHERE PaymentDate = '2025-03-20';

SELECT c.*, p.Amount, p.PaymentDate
FROM Payment p 
INNER JOIN Courier c ON p.CourierID = c.CourierID;

SELECT p.*, l.LocationName 
FROM Payment p 
INNER JOIN Location l ON p.LocationID = l.LocationID;

SELECT CourierID, SUM(Amount) AS TotalAmount
FROM Payment
GROUP BY CourierID;

SELECT * FROM Payment 
WHERE PaymentDate BETWEEN '2025-03-15' AND '2025-03-20';

SELECT u.*, c.*
FROM User u 
LEFT JOIN Courier c ON u.Name = c.SenderName
UNION
SELECT u.*, c.*
FROM User u 
RIGHT JOIN Courier c ON u.Name = c.SenderName;

SELECT c.*, s.*
FROM Courier c 
LEFT JOIN CourierServices s ON c.ServiceName = s.ServiceName
UNION
SELECT c.*, s.*
FROM Courier c 
RIGHT JOIN CourierServices s ON c.ServiceName = s.ServiceName;

SELECT e.*, p.*
FROM Employee e 
LEFT JOIN Payment p ON e.EmployeeID = p.CourierID
UNION
SELECT e.*, p.*
FROM Employee e 
RIGHT JOIN Payment p ON e.EmployeeID = p.CourierID;

SELECT u.*, s.*
FROM User u 
CROSS JOIN CourierServices s;

SELECT e.*, l.*
FROM Employee e 
CROSS JOIN Location l;

SELECT c.*, u.Name AS Sender 
FROM Courier c 
LEFT JOIN User u ON c.SenderName = u.Name;

SELECT c.*, u.Name AS Receiver 
FROM Courier c 
LEFT JOIN User u ON c.ReceiverName = u.Name;

SELECT c.*, s.*
FROM Courier c 
LEFT JOIN CourierServices s ON c.ServiceName = s.ServiceName;

SELECT e.Name, COUNT(c.CourierID) AS AssignedCouriers
FROM Employee e 
LEFT JOIN Courier c ON e.EmployeeID = c.EmployeeID
GROUP BY e.Name;

SELECT l.LocationName, SUM(p.Amount) AS TotalPayments
FROM Location l 
LEFT JOIN Payment p ON l.LocationID = p.LocationID
GROUP BY l.LocationName;

SELECT c1.* 
FROM Courier c1 
RIGHT JOIN Courier c2 ON c1.SenderName = c2.SenderName
WHERE c1.CourierID <> c2.CourierID;

SELECT e1.* 
FROM Employee e1 
RIGHT JOIN Employee e2 ON e1.Role = e2.Role
WHERE e1.EmployeeID <> e2.EmployeeID;

SELECT p.* 
FROM Payment p 
RIGHT JOIN Courier c ON p.CourierID = c.CourierID
WHERE c.SenderAddress = (SELECT SenderAddress FROM Courier WHERE CourierID = 1);

SELECT c.* 
FROM Courier c 
RIGHT JOIN Location l ON c.SenderAddress = l.Address;

SELECT e.Name, COUNT(c.CourierID) AS TotalDelivered
FROM Employee e 
RIGHT JOIN Courier c ON e.EmployeeID = c.EmployeeID
GROUP BY e.Name;

SELECT c.*, s.Cost, p.Amount
FROM Courier c 
LEFT JOIN CourierServices s ON c.ServiceName = s.ServiceName
LEFT JOIN Payment p ON c.CourierID = p.CourierID
WHERE p.Amount > s.Cost;