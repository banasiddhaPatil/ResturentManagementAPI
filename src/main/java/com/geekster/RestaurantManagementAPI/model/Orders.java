package com.geekster.RestaurantManagementAPI.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Orders.class,property="orderId")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private int orderQuantity;
    private String orderStatus;
    private LocalDateTime orderScheduleTime;
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private LocalDateTime orderCreationTime;

    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "fk_foodItem_id")
    FoodItem foodItem;
}