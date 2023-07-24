package com.geekster.RestaurantManagementAPI.repository;

import com.geekster.RestaurantManagementAPI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodItemRepo extends JpaRepository<FoodItem,Long> {
}
