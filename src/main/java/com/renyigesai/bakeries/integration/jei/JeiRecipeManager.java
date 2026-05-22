package com.renyigesai.bakeries.integration.jei;

import com.google.common.collect.Lists;
import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.BreadKnifeRecipe;
import com.renyigesai.bakeries.common.recipe.DoughCraftingTableRecipe;
import com.renyigesai.bakeries.common.recipe.FlourSieveRecipe;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import com.renyigesai.bakeries.common.recipe.drink.DrinkRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT,modid = BakeriesMod.MODID)
public final class JeiRecipeManager {
    public static List<RecipeHolder<BlenderRecipe>> BLENDERS;
    public static List<RecipeHolder<DoughCraftingTableRecipe>> DOUGH_CRAFTING_TABLES;
    public static List<RecipeHolder<OvenRecipe>> OVENS;
    public static List<RecipeHolder<BreadKnifeRecipe>> BREAD_KNIFES;
    public static List<RecipeHolder<DrinkRecipe>> DRINKS;
    public static List<RecipeHolder<FlourSieveRecipe>> FLOUR_SIEVE;

    @SubscribeEvent
    public static void onRecipesReceived(RecipesReceivedEvent event){
        RecipeMap recipeMap = event.getRecipeMap();
        BLENDERS = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.BLENDER_TYPE.get()));
        DOUGH_CRAFTING_TABLES = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.DOUGH_CRAFTING_TABLE_TYPE.get()));
        OVENS = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.OVEN_TYPE.get()));
        BREAD_KNIFES = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.BREAD_KNIFE_TYPE.get()));
        FLOUR_SIEVE = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.FLOUR_SIEVE_TYPE.get()));
        DRINKS = Lists.newArrayList(recipeMap.byType(BakeriesRecipes.DRINK_TYPE.get()));
    }

}
