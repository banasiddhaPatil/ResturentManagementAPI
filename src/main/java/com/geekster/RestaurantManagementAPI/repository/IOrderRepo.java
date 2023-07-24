package com.geekster.RestaurantManagementAPI.repository;


import com.geekster.RestaurantManagementAPI.model.Customer;
import com.geekster.RestaurantManagementAPI.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Long> {
    Orders findFirstByCustomer(Customer customer);
}
