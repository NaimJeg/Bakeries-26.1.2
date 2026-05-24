package com.renyigesai.bakeries.common.client.gui.overlay;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.blocks.toaster.ToasterBlock;
import com.renyigesai.bakeries.common.blocks.toaster.ToasterBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.util.List;

@SuppressWarnings("removal")
public class ToasterOverlay implements ILookOverlay<ToasterBlockEntity>{

    @Override
    public void create(RenderGuiEvent.Post event, ToasterBlockEntity entity, Player localPlayer, Minecraft mc) {
        GuiGraphicsExtractor guiGraphics = event.getGuiGraphics();
        int w = mc.getWindow().getGuiScaledWidth() / 2;
        int h = mc.getWindow().getGuiScaledHeight() / 2;
        if (localPlayer == null) {
            return;
        }
        String text = "";
        ToasterBlock.State state = entity.getBlockState().getValue(ToasterBlock.STATE);
        if (!entity.getItems().getStackInSlot(0).isEmpty() && state == ToasterBlock.State.IDLE){
            text = Component.translatable("tooltips.bakeries.toaster_0").getString();
        }else if (state == ToasterBlock.State.FINISH){
            text = Component.translatable("tooltips.bakeries.toaster_1", "Shift").getString();
        }
        List<FormattedCharSequence> tooltip = List.of(Component.literal(text).getVisualOrderText());
        List<ClientTooltipComponent> collect = tooltip.stream().map(ClientTooltipComponent::create).toList();
        if (!text.isEmpty()){
            int length = BakeriesMod.textMeasurer.getLength(text);
            tooltip(guiGraphics,mc.font,collect,w - length / 2 - 8,h + 64);
        }
    }

    @Override
    public boolean isOverlay(ToasterBlockEntity entity, Player localPlayer, Minecraft mc) {
        return true;
    }
}
