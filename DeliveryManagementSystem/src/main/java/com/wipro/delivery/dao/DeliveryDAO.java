package com.wipro.delivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.util.DBUtil;

public class DeliveryDAO {

   
    public boolean recordExists(String customerName, java.util.Date deliveryDate) {

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM DELIVERY_TB WHERE CUSTOMERNAME=? AND DELIVERY_DATE=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, customerName);
            ps.setDate(2, new Date(deliveryDate.getTime()));

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public String generateDeliveryID(String customerName, java.util.Date deliveryDate) {

        try {
            String datePart = new java.text.SimpleDateFormat("yyyyMMdd").format(deliveryDate);
            String namePart = customerName.substring(0, 2).toUpperCase();
            return datePart + namePart + (System.currentTimeMillis() % 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "DEL" + System.currentTimeMillis();
    }

    
    public String createRecord(DeliveryBean bean) {

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "INSERT INTO DELIVERY_TB "
                    + "(DELIVERYID, DELIVERYPERSON, CUSTOMERNAME, DELIVERYITEM, DELIVERY_DATE, STATUS, REMARKS) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, bean.getDeliveryId());
            ps.setString(2, bean.getDeliveryPerson());
            ps.setString(3, bean.getCustomerName());
            ps.setString(4, bean.getDeliveryItem());
            ps.setDate(5, new Date(bean.getDeliveryDate().getTime()));
            ps.setString(6, bean.getStatus());
            ps.setString(7, bean.getRemarks());

            int result = ps.executeUpdate();

            if (result > 0)
                return "SUCCESS";
            else
                return "FAIL";

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

   
    public DeliveryBean fetchRecord(String customerName, java.util.Date deliveryDate) {

        DeliveryBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM DELIVERY_TB WHERE CUSTOMERNAME=? AND DELIVERY_DATE=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, customerName);
            ps.setDate(2, new Date(deliveryDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                bean = new DeliveryBean();

                bean.setDeliveryId(rs.getString("DELIVERYID"));
                bean.setDeliveryPerson(rs.getString("DELIVERYPERSON"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setDeliveryItem(rs.getString("DELIVERYITEM"));
                bean.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    
    public List<DeliveryBean> fetchAllRecords() {

        List<DeliveryBean> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM DELIVERY_TB";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DeliveryBean bean = new DeliveryBean();

                bean.setDeliveryId(rs.getString("DELIVERYID"));
                bean.setDeliveryPerson(rs.getString("DELIVERYPERSON"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setDeliveryItem(rs.getString("DELIVERYITEM"));
                bean.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
