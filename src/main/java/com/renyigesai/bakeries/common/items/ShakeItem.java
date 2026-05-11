package com.renyigesai.bakeries.common.items;

import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ShakeItem extends Item {

    public final Supplier<Item> FINISH_ITEM;

    public ShakeItem(Properties pProperties, Supplier<Item> finishItem) {
        super(pProperties);
        FINISH_ITEM = finishItem;
    }

    public ItemStack getFinishItem() {
        return new ItemStack(this.FINISH_ITEM.get());
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 48;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return super.use(level, player, hand);
    }


    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        pStack.shrink(1);
        if (pLivingEntity instanceof Player player){
            player.getCooldowns().addCooldown(this.getFinishItem(),10);
            ItemUtils.givePlayerItem(player,this.getFinishItem());
        }

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltips.bakeries.shake").withStyle(ChatFormatting.BLUE));
    }

}
