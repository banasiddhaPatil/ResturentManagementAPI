package com.geekster.RestaurantManagementAPI.service;

import com.geekster.RestaurantManagementAPI.model.Customer;
import com.geekster.RestaurantManagementAPI.model.Orders;
import com.geekster.RestaurantManagementAPI.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo iOrderRepo;





    public List<Orders> getAllOrders() {
        return iOrderRepo.findAll();
    }

    public void saveOrder(Orders order) {

        order.setOrderCreationTime(LocalDateTime.now());
        iOrderRepo.save(order);
    }

    public Orders getOrderForCustomer(Customer customer) {
        return iOrderRepo.findFirstByCustomer(customer);
    }

    public void cancelOrder(Orders order) {

        iOrderRepo.delete(order);
    }

}
