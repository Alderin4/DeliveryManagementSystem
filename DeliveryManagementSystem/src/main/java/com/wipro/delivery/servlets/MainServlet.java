package com.wipro.delivery.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if (operation == null) {
            response.sendRedirect("error.html");
            return;
        }

        try {

            switch (operation) {

            
            case "newRecord":

                String result = addRecord(request);
                System.out.println("RESULT = " + result);
                if (result.equals("SUCCESS")) {
                    response.sendRedirect("success.html");
                } else {
                    response.sendRedirect("error.html");
                }
                break;

            
            case "viewRecord":

                DeliveryBean bean = viewRecord(request);

                if (bean == null) {
                    request.setAttribute("message",
                            "No matching records exist! Please try again!");
                } else {
                    request.setAttribute("deliveryBean", bean);
                }

                RequestDispatcher rd1 =
                        request.getRequestDispatcher("displayDelivery.jsp");
                rd1.forward(request, response);
                break;

            
            case "viewAllRecords":

                List<DeliveryBean> list = admin.viewAllRecords();

                if (list == null || list.isEmpty()) {
                    request.setAttribute("message",
                            "No records available!");
                } else {
                    request.setAttribute("deliveryList", list);
                }

                RequestDispatcher rd2 =
                        request.getRequestDispatcher("displayAllDeliveries.jsp");
                rd2.forward(request, response);
                break;

            default:
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    
    private String addRecord(HttpServletRequest request) {

        try {

            String deliveryPerson = request.getParameter("deliveryPerson");
            String customerName = request.getParameter("customerName");
            String deliveryItem = request.getParameter("deliveryItem");
            String deliveryDateStr = request.getParameter("deliveryDate");
            String status = request.getParameter("status");
            String remarks = request.getParameter("remarks");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date deliveryDate = sdf.parse(deliveryDateStr);

            DeliveryBean bean = new DeliveryBean();
            bean.setDeliveryPerson(deliveryPerson);
            bean.setCustomerName(customerName);
            bean.setDeliveryItem(deliveryItem);
            bean.setDeliveryDate(deliveryDate);
            bean.setStatus(status);
            bean.setRemarks(remarks);

            Administrator admin = new Administrator();
            return admin.addRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }



    
    private DeliveryBean viewRecord(HttpServletRequest request) {

        try {

            String customerName = request.getParameter("customerName");
            String dateStr = request.getParameter("deliveryDate");

            if (customerName == null || dateStr == null ||
                customerName.trim().isEmpty() || dateStr.trim().isEmpty()) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date deliveryDate = sdf.parse(dateStr);

            return admin.viewRecord(customerName, deliveryDate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
