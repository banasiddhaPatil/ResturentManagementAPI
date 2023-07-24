package com.geekster.RestaurantManagementAPI.repository;

import com.geekster.RestaurantManagementAPI.model.AuthenticationToken;
import com.geekster.RestaurantManagementAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByCustomer(Customer customer);
}
