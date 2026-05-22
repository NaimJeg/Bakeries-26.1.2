package com.renyigesai.bakeries.api.items;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.manager.ClientAdvancementsManager;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.Map;
import java.util.function.Consumer;

public class RawItem extends Item {
    public final int minTemperature;
    public final int perfectTemperature;
    public String advancementName;
    public RawItem(Properties properties, int minTemperature, int perfectTemperature, String advancementName) {
        super(properties);
        this.minTemperature = minTemperature;
        this.perfectTemperature = perfectTemperature;
        this.advancementName = advancementName;
    }

    public RawItem(Properties properties, int minTemperature) {
        super(properties);
        this.minTemperature = minTemperature;
        this.perfectTemperature = -1;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltips.bakeries.row_item_temperature",String.valueOf(minTemperature)).withStyle(ChatFormatting.BLUE));

        try {
            if (advancementName != null && perfectTemperature != -1) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    ClientAdvancements advancements = player.connection.getAdvancements();
                    Map<AdvancementHolder, AdvancementProgress> progress = ((ClientAdvancementsManager) advancements).getProgress();
                    Identifier path = Identifier.fromNamespaceAndPath(BakeriesMod.MODID, "perfect_temperature/" + advancementName);
                    AdvancementHolder advancementHolder = advancements.get(path);
                    if (advancementHolder == null) {
                        return;
                    }
                    AdvancementProgress advancementProgress = progress.get(advancementHolder);
                    if (advancementProgress == null) {
                        return;
                    }
                    if (advancementProgress.isDone()) {
                        builder.accept(Component.translatable("tooltips.bakeries.raw_item_perfect_temperature",String.valueOf(perfectTemperature)).withStyle(ChatFormatting.GOLD));
                    }
                }
            }
        }catch (Exception e){
            BakeriesMod.LOGGER.error(String.valueOf(e));
        }

    }
}
