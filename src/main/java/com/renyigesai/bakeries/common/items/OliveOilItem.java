package com.renyigesai.bakeries.common.items;

import com.renyigesai.bakeries.api.items.PileItem;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

public class OliveOilItem extends PileItem {

    public OliveOilItem(Identifier identifier) {
        super(BakeriesBlocks.OLIVE_OIL.get(), new Properties().durability(6).setId(ResourceKey.create(Registries.ITEM,identifier)));
    }

    @Override
    public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        int maxDamage = instance.getOrDefault(DataComponents.MAX_DAMAGE, -1);
        int damage = instance.getOrDefault(DataComponents.DAMAGE, -1);
        if (maxDamage != -1 && damage != -1){
            if (damage + 1 >= maxDamage){
                return new ItemStackTemplate(Items.GLASS_BOTTLE);
            }else {
                return new ItemStackTemplate(this, DataComponentPatch.builder().set(DataComponents.DAMAGE,damage + 1).build());
            }
        }
        return super.getCraftingRemainder(instance);
    }

    @Override
    public boolean isExtra(UseOnContext pContext) {
        return pContext.getItemInHand().getDamageValue() == 0;
    }


    @Override
    public boolean isCombineRepairable(ItemStack stack) {
        return false;
    }
}
