package com.renyigesai.bakeries.common.recipe.blender;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public class BlenderInput implements RecipeInput {
    private final List<ItemStack> items;
    private static final int INPUT_SIZE = 9;

    public BlenderInput(List<ItemStack> items) {
        this.items = items;
    }


    @Override
    public ItemStack getItem(int index) {
        return index >= 0 && index < INPUT_SIZE ? items.get(index) : ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return items.size();
    }
}