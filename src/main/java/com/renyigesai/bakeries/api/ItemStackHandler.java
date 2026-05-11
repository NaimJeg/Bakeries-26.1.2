package com.renyigesai.bakeries.api;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class ItemStackHandler {
    private NonNullList<ItemStack> items;

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public ItemStackHandler(int size) {
        this.items = NonNullList.withSize(size,ItemStack.EMPTY);
    }

    public int getSlots(){
        return this.items.size();
    }

    public ItemStack getStackInSlot(int slot){
        return this.items.get(slot);
    }

    public void setStackInSlot(int slot,ItemStack stack){
        this.items.set(slot,stack);
    }
}
