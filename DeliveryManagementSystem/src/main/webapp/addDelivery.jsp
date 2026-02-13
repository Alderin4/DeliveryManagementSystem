<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Delivery Record</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        h2 {
            color: #333;
        }
        form {
            width: 400px;
        }
        table {
            width: 100%;
        }
        td {
            padding: 10px;
        }
        input[type="text"], input[type="date"], textarea {
            width: 100%;
            padding: 5px;
        }
        input[type="submit"], input[type="reset"] {
            padding: 8px 20px;
            margin: 5px;
        }
        .back-link {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Add Delivery Record</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="newRecord">
        <table>
            <tr>
                <td>Delivery Person:</td>
                <td><input type="text" name="deliveryPerson" required></td>
            </tr>
            <tr>
                <td>Customer Name:</td>
                <td><input type="text" name="customerName" required></td>
            </tr>
            <tr>
                <td>Delivery Item:</td>
                <td><input type="text" name="deliveryItem" required></td>
            </tr>
            <tr>
                <td>Delivery Date:</td>
                <td><input type="date" name="deliveryDate" required></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td><input type="text" name="status" required></td>
            </tr>
            <tr>
                <td>Remarks:</td>
                <td><textarea name="remarks" rows="3" required></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add Record">
                    <input type="reset" value="Clear">
                </td>
            </tr>
        </table>
    </form>
    <div class="back-link">
        <a href="menu.html">Back to Menu</a>
    </div>
</body>
</html>
