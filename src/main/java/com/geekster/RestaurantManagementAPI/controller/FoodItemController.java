package com.geekster.RestaurantManagementAPI.controller;

import com.geekster.RestaurantManagementAPI.model.FoodItem;
import com.geekster.RestaurantManagementAPI.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("foodItem")
    void addFoodItem(@RequestBody FoodItem foodItem)
    {
        foodItemService.addFoodItem(foodItem);
    }

    @GetMapping("foodItems")
    List<FoodItem> getAllFoodItems()
    {
        return foodItemService.getAllFoodItem();
    }



}
