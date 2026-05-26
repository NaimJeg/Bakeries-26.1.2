package com.renyigesai.bakeries.common.init;

import net.minecraft.world.food.FoodProperties;

public class BakeriesFoodProperties {
    public static final FoodProperties BAGEL;
    public static final FoodProperties WHOLE_WHEAT_BAGEL;
    public static final FoodProperties BAGUETTE;
    public static final FoodProperties BROWN_SUGAR_ROLL;
    public static final FoodProperties COUNTRY_BREAD;
    public static final FoodProperties CROISSANT;
    public static final FoodProperties CIABATTA;
    public static final FoodProperties PINEAPPLE_BUN;
    public static final FoodProperties ROUND_BREAD;
    public static final FoodProperties SALT_CROISSANT;
    public static final FoodProperties SLICED_TOAST;
    public static final FoodProperties BERRY_BREAD;
    public static final FoodProperties COUNTRY_BREAD_SLICE;
    public static final FoodProperties OLIVE;
    public static final FoodProperties TOMATO;
    public static final FoodProperties CHEESE_CUBE;
    public static final FoodProperties SLICED_CHEESE_COCOA_TOAST;
    public static final FoodProperties MEAT_FLOSS_BREAD;
    public static final FoodProperties MEAT_FLOSS;
    public static final FoodProperties ICED_AMERICAN;
    public static final FoodProperties ICED_LATTE;
    public static final FoodProperties BROWN_SUGAR_LATTE;
    public static final FoodProperties FOCACCIA;
    public static final FoodProperties DIRTY_CHOCO_CROISSANT;
    public static final FoodProperties BAGUETTE_WITH_FILLING;
    public static final FoodProperties CREAM_BINGLE_COFFEE;
    public static final FoodProperties TOMATO_CHEESE_CROISSANT_SANDWICH;
    public static final FoodProperties BERRY_BAGEL;
    public static final FoodProperties HONEY_BUTTER_SPREAD_TOAST;
    public static final FoodProperties HONEY_BUTTER_SPREAD_COUNTRY_BREAD;
    public static final FoodProperties SCONE;
    public static final FoodProperties CUP_CAKE;
    public static final FoodProperties CAKE_ROLL;
    public static final FoodProperties FOAMED_CREAM;
    public static final FoodProperties SLICED_POUND_CAKE;
    public static final FoodProperties CREAM_CAKE_CUBE;
    public static final FoodProperties CHEESE_CREAM_BREAD;
    public static final FoodProperties MATCHA_LATTE;
    public static final FoodProperties MATCHA_PARFAIT;
    public static final FoodProperties BAGEL_FILLED_SAUCE;
    public static final FoodProperties EGG_TART;
    public static final FoodProperties PINEAPPLE_OIL;
    public static final FoodProperties FLAT_CROISSANT;
    public static final FoodProperties COOKED_TARO;
    public static final FoodProperties MASHED_TARO;
    public static final FoodProperties TARO_SALT_YOLK_BREAD;
    public static final FoodProperties TARO_MILK;

    /*模组联动食物属性*/
    public static final FoodProperties RICE_BREAD;
//    public static final FoodProperties RICE_BREAD_FARMERSDELIGHT;

    public static final FoodProperties ORANGE_AMERICAN;

    public static final FoodProperties GARLIC_FLAVORED_BAGUETTE;

    public static final FoodProperties YUNTUI_MOONCAKE;


    static {
        BAGEL = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5f).build();

        WHOLE_WHEAT_BAGEL = new FoodProperties.Builder().nutrition(8).saturationModifier(0.35f).build();

        BAGUETTE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.25f).build();

        BROWN_SUGAR_ROLL = new FoodProperties.Builder().nutrition(7).saturationModifier(0.5f).build();

        COUNTRY_BREAD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();

        CROISSANT = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();

        PINEAPPLE_BUN = new FoodProperties.Builder().nutrition(6).saturationModifier(1.5f).build();

        ROUND_BREAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).build();

        SALT_CROISSANT = new FoodProperties.Builder().nutrition(6).saturationModifier(1.0f).build();

        SLICED_TOAST = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).alwaysEdible().build();

        SLICED_CHEESE_COCOA_TOAST = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8f).alwaysEdible().build();

        BERRY_BREAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.7f).build();

        COUNTRY_BREAD_SLICE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();

        CIABATTA = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();

        MEAT_FLOSS_BREAD = new FoodProperties.Builder().nutrition(7).saturationModifier(0.75f).alwaysEdible().build();

        FOCACCIA = new FoodProperties.Builder().nutrition(8).saturationModifier(1f).build();

        OLIVE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();

        TOMATO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();

        CHEESE_CUBE = new FoodProperties.Builder().nutrition(1).saturationModifier(1f).build();

        MEAT_FLOSS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.8f).build();

        ICED_AMERICAN = new FoodProperties.Builder().alwaysEdible().build();

        ICED_LATTE = new FoodProperties.Builder().alwaysEdible().build();

        BROWN_SUGAR_LATTE = new FoodProperties.Builder().alwaysEdible().build();

        CREAM_BINGLE_COFFEE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).alwaysEdible().build();

        MATCHA_LATTE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).alwaysEdible().build();

        MATCHA_PARFAIT = new FoodProperties.Builder().nutrition(9).saturationModifier(0.5f).alwaysEdible().build();

        DIRTY_CHOCO_CROISSANT = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();

        BAGUETTE_WITH_FILLING = new FoodProperties.Builder().nutrition(13).saturationModifier(0.4f).alwaysEdible().build();

        TOMATO_CHEESE_CROISSANT_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationModifier(0.9f).alwaysEdible().build();

        BERRY_BAGEL = new FoodProperties.Builder().nutrition(8).saturationModifier(0.625f).alwaysEdible().build();

        HONEY_BUTTER_SPREAD_TOAST = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).alwaysEdible().build();

        HONEY_BUTTER_SPREAD_COUNTRY_BREAD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.65f).alwaysEdible().build();

        SCONE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).alwaysEdible().build();

        CUP_CAKE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).alwaysEdible().build();

        CAKE_ROLL = new FoodProperties.Builder().nutrition(12).saturationModifier(0.3f).alwaysEdible().build();

        FOAMED_CREAM = new FoodProperties.Builder().nutrition(1).saturationModifier(1f).alwaysEdible().build();

        SLICED_POUND_CAKE = new FoodProperties.Builder().nutrition(1).saturationModifier(1f).alwaysEdible().build();

        CREAM_CAKE_CUBE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).alwaysEdible().build();

        CHEESE_CREAM_BREAD = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).alwaysEdible().build();

        BAGEL_FILLED_SAUCE = new FoodProperties.Builder().nutrition(12).saturationModifier(0.4f).alwaysEdible().build();

        EGG_TART = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).build();

        PINEAPPLE_OIL = new FoodProperties.Builder().nutrition(8).saturationModifier(1.5f).alwaysEdible().build();

        FLAT_CROISSANT = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).alwaysEdible().build();

        COOKED_TARO = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build();

        MASHED_TARO = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).build();

        TARO_SALT_YOLK_BREAD = new FoodProperties.Builder().nutrition(10).saturationModifier(1f).alwaysEdible().build();

        TARO_MILK = new FoodProperties.Builder().nutrition(9).saturationModifier(0.5f).alwaysEdible().build();


        /*模组联动食物属性*/
        RICE_BREAD = new FoodProperties.Builder().nutrition(12).saturationModifier(0.4f).build();
//        RICE_BREAD_FARMERSDELIGHT = new FoodProperties.Builder().nutrition(12).saturationModifier(0.4f).build();

        ORANGE_AMERICAN = new FoodProperties.Builder().alwaysEdible().build();

        GARLIC_FLAVORED_BAGUETTE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).build();

        YUNTUI_MOONCAKE = new FoodProperties.Builder().nutrition(3).saturationModifier(1.0f).build();
    }
}