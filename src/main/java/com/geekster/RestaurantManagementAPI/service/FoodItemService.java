package com.geekster.RestaurantManagementAPI.service;

import com.geekster.RestaurantManagementAPI.model.FoodItem;
import com.geekster.RestaurantManagementAPI.repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepo iFoodItemRepo;


    public void addFoodItem(FoodItem foodItem) {
        iFoodItemRepo.save(foodItem);
    }

    public List<FoodItem> getAllFoodItem() {
        return iFoodItemRepo.findAll();
    }
}
