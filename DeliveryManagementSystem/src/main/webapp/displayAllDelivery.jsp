<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wipro.delivery.bean.DeliveryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Delivery Records</title>
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
            width: 100%;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
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
        .info-message {
            color: #0066cc;
            font-size: 18px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h2>All Delivery Records</h2>
    
    <%
        List<DeliveryBean> deliveryList = (List<DeliveryBean>) request.getAttribute("deliveryList");
        String infoMessage = (String) request.getAttribute("infoMessage");
        
        if (infoMessage != null) {
    %>
        <div class="info-message">
            <%= infoMessage %>
        </div>
    <%
        } else if (deliveryList != null && !deliveryList.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    %>
        <table>
            <thead>
                <tr>
                    <th>Delivery ID</th>
                    <th>Delivery Person</th>
                    <th>Customer Name</th>
                    <th>Delivery Item</th>
                    <th>Delivery Date</th>
                    <th>Status</th>
                    <th>Remarks</th>
                </tr>
            </thead>
            <tbody>
    <%
            for (DeliveryBean bean : deliveryList) {
    %>
                <tr>
                    <td><%= bean.getDeliveryId() %></td>
                    <td><%= bean.getDeliveryPerson() %></td>
                    <td><%= bean.getCustomerName() %></td>
                    <td><%= bean.getDeliveryItem() %></td>
                    <td><%= sdf.format(bean.getDeliveryDate()) %></td>
                    <td><%= bean.getStatus() != null ? bean.getStatus() : "N/A" %></td>
                    <td><%= bean.getRemarks() != null ? bean.getRemarks() : "N/A" %></td>
                </tr>
    <%
            }
    %>
            </tbody>
        </table>
        <p><strong>Total Records: <%= deliveryList.size() %></strong></p>
    <%
        }
    %>
    
    <div class="back-link">
        <a href="viewAllDeliveries.jsp">Refresh</a> | 
        <a href="menu.html">Back to Menu</a>
    </div>
</body>
</html>
