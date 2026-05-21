package com.renyigesai.bakeries.common.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MokaPotFillItem extends Item {
    public MokaPotFillItem(Identifier identifier) {
        super(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,identifier)));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 5592575;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return 13;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

}
