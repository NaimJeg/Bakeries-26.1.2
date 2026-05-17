package com.renyigesai.bakeries.common.recipe;

import com.mojang.serialization.MapCodec;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public class DoughCraftingTableRecipe extends SingleItemRecipe {
    public static final MapCodec<DoughCraftingTableRecipe> MAP_CODEC = simpleMapCodec(DoughCraftingTableRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, DoughCraftingTableRecipe> STREAM_CODEC = simpleStreamCodec(DoughCraftingTableRecipe::new);
    public static final RecipeSerializer<DoughCraftingTableRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public DoughCraftingTableRecipe(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result) {
        super(commonInfo, ingredient, result);
    }

    @Override
    public RecipeType<DoughCraftingTableRecipe> getType() {
        return BakeriesRecipes.DOUGH_CRAFTING_TABLE_TYPE.get();
    }

    @Override
    public RecipeSerializer<DoughCraftingTableRecipe> getSerializer() {
        return BakeriesRecipes.DOUGH_CRAFTING_TABLE_SERIALIZER.get();
    }

    @Override
    public String group() {
        return "";
    }

    public SlotDisplay resultDisplay() {
        return new SlotDisplay.ItemStackSlotDisplay(this.result());
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

}
