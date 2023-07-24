package com.geekster.RestaurantManagementAPI.service;

import com.geekster.RestaurantManagementAPI.model.AuthenticationToken;
import com.geekster.RestaurantManagementAPI.model.Customer;
import com.geekster.RestaurantManagementAPI.model.Dto.SignInInput;
import com.geekster.RestaurantManagementAPI.model.Dto.SignUpOutput;
import com.geekster.RestaurantManagementAPI.model.Orders;
import com.geekster.RestaurantManagementAPI.repository.IAuthTokenRepo;
import com.geekster.RestaurantManagementAPI.repository.ICustomerRepo;
import com.geekster.RestaurantManagementAPI.repository.IFoodItemRepo;
import com.geekster.RestaurantManagementAPI.service.emailUtility.EmailHandler;
import com.geekster.RestaurantManagementAPI.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;

    @Autowired
    IFoodItemRepo iFoodItemRepo;

    @Autowired
    IAuthTokenRepo authTokenRepo;

    @Autowired
    OrderService orderService;

    public SignUpOutput signUpCustomer(Customer customer) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = customer.getCustomerEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this customer email already exists ??
        Customer existingCustomer = iCustomerRepo.findFirstByCustomerEmail(newEmail);

        if(existingCustomer != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(customer.getCustomerPassword());

            //saveOrder the Customer with the new encrypted password

            customer.setCustomerPassword(encryptedPassword);
            iCustomerRepo.save(customer);

            return new SignUpOutput(signUpStatus, "Customer registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public List<Customer> getAllCustomers() {
        return iCustomerRepo.findAll();
    }


    public String signInCustomer(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this customer email already exists ??
        Customer existingCustomer = iCustomerRepo.findFirstByCustomerEmail(signInEmail);

        if(existingCustomer == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(signInInput.getPassword());
            if(existingCustomer.getCustomerPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingCustomer);
                authTokenRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public boolean scheduleOrder(Orders order) {
        //id of foodItem
        Long foodItemId = order.getFoodItem().getFoodItemId();
        boolean isDoctorValid = iFoodItemRepo.existsById(foodItemId);

        //id of customer
        Long patientId = order.getCustomer().getCustomerId();
        boolean isPatientValid = iCustomerRepo.existsById(patientId);

        if(isDoctorValid && isPatientValid)
        {
            orderService.saveOrder(order);
            return true;
        }
        else {
            return false;
        }
    }

    public void cancelOrder(String email) {

        //email -> Customer
        Customer customer = iCustomerRepo.findFirstByCustomerEmail(email);

        Orders order = orderService.getOrderForCustomer(customer);

        orderService.cancelOrder(order);



    }

    public String sigOutCustomer(String email) {

        Customer customer = iCustomerRepo.findFirstByCustomerEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByCustomer(customer));
        return "Customer Signed out successfully";
    }
}
