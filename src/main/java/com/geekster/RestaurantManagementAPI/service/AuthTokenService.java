package com.geekster.RestaurantManagementAPI.service;

import com.geekster.RestaurantManagementAPI.model.AuthenticationToken;
import com.geekster.RestaurantManagementAPI.repository.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    @Autowired
    IAuthTokenRepo authTokenRepo;

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = authTokenRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getCustomer().getCustomerEmail();

        return tokenConnectedEmail.equals(email);
    }
}
