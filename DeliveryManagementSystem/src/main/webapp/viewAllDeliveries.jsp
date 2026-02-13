<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View All Delivery Records</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        h2 {
            color: #333;
        }
        form {
            margin: 30px 0;
        }
        input[type="submit"] {
            padding: 10px 30px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .back-link {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>View All Delivery Records</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewAllRecords">
        <input type="submit" value="View All Records">
    </form>
    
    <div class="back-link">
        <a href="menu.html">Back to Menu</a>
    </div>
</body>
</html>
