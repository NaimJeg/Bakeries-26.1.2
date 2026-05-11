package com.renyigesai.bakeries.api;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;

public class FoodData {
    public final FoodProperties foodProperties;
    public final Consumable consumable;

    public FoodData(FoodProperties foodProperties, Consumable consumable) {
        this.foodProperties = foodProperties;
        this.consumable = consumable;
    }

    public FoodData(FoodProperties foodProperties) {
        this.foodProperties = foodProperties;
        this.consumable = Consumable.builder().build();
    }

    public static FoodData create(FoodProperties foodProperties){
        return new FoodData(foodProperties);
    }

    public static FoodData create(FoodProperties foodProperties,Consumable consumable){
        return new FoodData(foodProperties,consumable);
    }
}
