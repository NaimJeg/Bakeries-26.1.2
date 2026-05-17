package com.renyigesai.bakeries.api.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class RawItem extends Item {
    public final int minTemperature;
    public RawItem(Properties properties, int minTemperature) {
        super(properties);
        this.minTemperature = minTemperature;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltips.bakeries.row_item_temperature",String.valueOf(minTemperature)).withStyle(ChatFormatting.BLUE));
    }
}
