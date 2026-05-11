package com.renyigesai.bakeries.api.items;

import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BottleButterItem extends Item {
    public BottleButterItem(Identifier identifier) {
        super(new Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(ResourceKey.create(Registries.ITEM,identifier)));
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()){
            ItemUtils.shrink(itemInHand,1,pPlayer);
            ItemUtils.givePlayerItem(pPlayer,new ItemStack(BakeriesItems.BUTTER_CUBE.get()));
            return InteractionResult.SUCCESS;
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
