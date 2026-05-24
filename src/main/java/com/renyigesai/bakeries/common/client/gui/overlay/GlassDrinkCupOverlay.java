package com.renyigesai.bakeries.common.client.gui.overlay;

import com.renyigesai.bakeries.common.blocks.glass_drink_cup.GlassDrinkCupBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
@SuppressWarnings("removal")
public class GlassDrinkCupOverlay implements ILookOverlay<GlassDrinkCupBlockEntity>{

    @Override
    public void create(RenderGuiEvent.Post event, GlassDrinkCupBlockEntity entity, Player localPlayer, Minecraft mc) {
        GuiGraphicsExtractor guiGraphics = event.getGuiGraphics();
        int w =  mc.getWindow().getGuiScaledWidth() / 2 - 71;
        int h = mc.getWindow().getGuiScaledHeight() / 2 + 50;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,Identifier.fromNamespaceAndPath("bakeries","textures/gui/glass_drink_cup_overlay.png"), w, h, 0, 0, 142, 22, 142, 22);
        int x = w + 3;
        int y = h + 3;
        for (int i = 0; i < 4; ++i) {
            guiGraphics.fakeItem(entity.getInventory().getStackInSlot(i),x + (i * 24),y, -1);
        }
        guiGraphics.fakeItem(entity.getInventory().getStackInSlot(4),x + (5 * 24),y, -1);
    }

    @Override
    public boolean isOverlay(GlassDrinkCupBlockEntity entity, Player localPlayer, Minecraft mc) {
        return true;
    }
}
