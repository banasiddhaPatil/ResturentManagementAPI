package com.geekster.RestaurantManagementAPI.repository;

import com.geekster.RestaurantManagementAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
    Customer findFirstByCustomerEmail(String newEmail);
}
