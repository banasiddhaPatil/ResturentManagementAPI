package com.geekster.RestaurantManagementAPI.repository;

import com.geekster.RestaurantManagementAPI.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Long> {
}
