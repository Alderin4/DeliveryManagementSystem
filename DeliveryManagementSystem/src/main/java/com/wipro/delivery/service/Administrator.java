package com.wipro.delivery.service;

import java.util.Date;
import java.util.List;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.dao.DeliveryDAO;
import com.wipro.delivery.util.InvalidInputException;

public class Administrator {

    DeliveryDAO dao = new DeliveryDAO();

    
    public String addRecord(DeliveryBean bean) {

        try {

            
            if (bean == null ||
                bean.getDeliveryPerson() == null || bean.getDeliveryPerson().trim().isEmpty() ||
                bean.getCustomerName() == null || bean.getCustomerName().trim().isEmpty() ||
                bean.getDeliveryDate() == null ||
                bean.getDeliveryItem() == null || bean.getDeliveryItem().trim().isEmpty() 
                ) {

                throw new InvalidInputException();
            }

            
            if (bean.getCustomerName().length() < 2) {
                return "INVALID CUSTOMER NAME";
            }

            if (bean.getDeliveryPerson().length() < 2) {
                return "INVALID DELIVERY PERSON";
            }

            if (bean.getDeliveryItem().length() < 2) {
                return "INVALID DELIVERY ITEM";
            }

            
            if (dao.recordExists(bean.getCustomerName(), bean.getDeliveryDate())) {
                return "ALREADY EXISTS";
            }

            
            String deliveryId = dao.generateDeliveryID(
                    bean.getCustomerName(),
                    bean.getDeliveryDate());

            if (deliveryId == null) {
                return "FAIL";
            }

            bean.setDeliveryId(deliveryId);

            
            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    
    public DeliveryBean viewRecord(String customerName, Date deliveryDate) {

        if (customerName == null || customerName.trim().isEmpty() || deliveryDate == null) {
            return null;
        }

        return dao.fetchRecord(customerName, deliveryDate);
    }

    
    public List<DeliveryBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }
}
