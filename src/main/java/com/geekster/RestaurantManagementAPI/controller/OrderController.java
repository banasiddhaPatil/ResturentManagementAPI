package com.geekster.RestaurantManagementAPI.controller;

import com.geekster.RestaurantManagementAPI.model.Orders;
import com.geekster.RestaurantManagementAPI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;


    @GetMapping("orders")
    List<Orders> getAllOrders()
    {
        return orderService.getAllOrders();
    }
}
