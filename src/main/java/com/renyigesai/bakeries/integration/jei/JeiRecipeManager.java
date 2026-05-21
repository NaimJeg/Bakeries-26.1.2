package com.renyigesai.bakeries.integration.jei;

import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.DoughCraftingTableRecipe;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class JeiRecipeManager {


    public RecipeAccess access;
    public JeiRecipeManager() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;

        if (level != null) {
            access = level.recipeAccess();
        } else {
            throw new NullPointerException("Minecraft level must not be null.");
        }
    }

    public List<RecipeHolder<BlenderRecipe>> getAllBlenderRecipe(){
        if (access instanceof RecipeManager manager){
            return manager.getRecipes().stream().filter(holder -> holder.value().getType() == BakeriesRecipes.BLENDER_TYPE.get()).map(holder -> (RecipeHolder<BlenderRecipe>) holder).toList();
        }
        return List.of();
    }

}
