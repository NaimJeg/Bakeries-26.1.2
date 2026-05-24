package com.renyigesai.bakeries.common.client.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

import java.util.List;
import java.util.stream.Collectors;

public interface ILookOverlay<T extends BlockEntity> {

    void create(RenderGuiEvent.Post event, T entity, Player localPlayer, Minecraft mc);

    boolean isOverlay(T entity, Player localPlayer, Minecraft mc);

    /**简易调用*/
    default void tooltip(GuiGraphicsExtractor graphicsExtractor,Font font,List<ClientTooltipComponent> list,int x,int y){
        graphicsExtractor.tooltip(font,list,x,y,DefaultTooltipPositioner.INSTANCE,null);
    }

}
