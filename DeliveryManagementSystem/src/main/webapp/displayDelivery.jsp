<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wipro.delivery.bean.DeliveryBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delivery Record Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        h2 {
            color: #333;
        }
        table {
            border-collapse: collapse;
            width: 600px;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .back-link {
            margin-top: 20px;
        }
        .error-message {
            color: red;
            font-size: 18px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h2>Delivery Record Details</h2>
    
    <%
        DeliveryBean bean = (DeliveryBean) request.getAttribute("deliveryBean");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        if (errorMessage != null) {
    %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
    <%
        } else if (bean != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    %>
        <table>
            <tr>
                <th>Field</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>Delivery ID</td>
                <td><%= bean.getDeliveryId() %></td>
            </tr>
            <tr>
                <td>Delivery Person</td>
                <td><%= bean.getDeliveryPerson() %></td>
            </tr>
            <tr>
                <td>Customer Name</td>
                <td><%= bean.getCustomerName() %></td>
            </tr>
            <tr>
                <td>Delivery Item</td>
                <td><%= bean.getDeliveryItem() %></td>
            </tr>
            <tr>
                <td>Delivery Date</td>
                <td><%= sdf.format(bean.getDeliveryDate()) %></td>
            </tr>
            <tr>
                <td>Status</td>
                <td><%= bean.getStatus() != null ? bean.getStatus() : "N/A" %></td>
            </tr>
            <tr>
                <td>Remarks</td>
                <td><%= bean.getRemarks() != null ? bean.getRemarks() : "N/A" %></td>
            </tr>
        </table>
    <%
        }
    %>
    
    <div class="back-link">
        <a href="viewDelivery.jsp">Search Again</a> | 
        <a href="menu.html">Back to Menu</a>
    </div>
</body>
</html>
