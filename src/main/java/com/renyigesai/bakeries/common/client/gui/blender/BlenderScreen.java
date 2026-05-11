package com.renyigesai.bakeries.common.client.gui.blender;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class BlenderScreen extends AbstractContainerScreen<BlenderMenu> {
    private static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/blender/blender_gui.png");
    public BlenderScreen(BlenderMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = this.leftPos;
        int yo = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
    }
}
