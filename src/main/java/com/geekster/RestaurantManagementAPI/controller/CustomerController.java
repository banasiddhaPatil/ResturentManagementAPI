package com.geekster.RestaurantManagementAPI.controller;

import com.geekster.RestaurantManagementAPI.model.Customer;
import com.geekster.RestaurantManagementAPI.model.Dto.SignInInput;
import com.geekster.RestaurantManagementAPI.model.Dto.SignUpOutput;
import com.geekster.RestaurantManagementAPI.model.Orders;
import com.geekster.RestaurantManagementAPI.service.AuthTokenService;
import com.geekster.RestaurantManagementAPI.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AuthTokenService authTokenService;



    @PostMapping("customer/signup")
    public SignUpOutput signUpCustomer(@RequestBody Customer customer)
    {

        return customerService.signUpCustomer(customer);
    }

    @PostMapping("customer/signIn")
    public String signInCustomer(@RequestBody @Valid SignInInput signInInput)
    {
        return customerService.signInCustomer(signInInput);
    }

    @DeleteMapping("customer/signOut")
    public String sigOutPatient(String email, String token)
    {
        if(authTokenService.authenticate(email,token)) {
            return customerService.sigOutCustomer(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    @GetMapping("customers")
    List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @PostMapping("order/schedule")
    public String  scheduleOrder(@RequestBody Orders order, String email, String token)
    {

        if(authTokenService.authenticate(email,token)) {
            boolean status = customerService.scheduleOrder(order);
            return status ? "Order scheduled":"error occurred during scheduling order";
        }
        else
        {
            return "Scheduling failed because invalid authentication";
        }
    }

    @DeleteMapping("order/cancel")
    public String  cancelOrder(String email, String token)
    {

        if(authTokenService.authenticate(email,token)) {
            customerService.cancelOrder(email);
            return "cancelled Order successfully";
        }
        else
        {
            return "Scheduling failed because invalid authentication";
        }
    }
}
