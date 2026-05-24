package com.renyigesai.bakeries.common.client.gui.blender;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class BlenderScreen extends AbstractContainerScreen<BlenderMenu> {
    private static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/blender/blender_gui.png");
    private static final Identifier FLOAT_PANEL = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/blender/blender_float_panel.png");

    public boolean isDragged;
    public int pressTime = 0;
    public boolean isFiltration = false;
    public boolean isMove = false;
    private int dragOffsetX, dragOffsetY;
    private int filtrationX = 0;
    private int filtrationY = 0;
    private static final int FILTRATION_WIDTH = 63;
    private static final int FILTRATION_HEIGHT = 90;

    public BlenderScreen(BlenderMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        ImageButton filtration = new ImageButton(this.leftPos + 15, this.topPos + 30, 24, 24, new WidgetSprites(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "blender/blender_filtration_button_0"), ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "blender/blender_filtration_button_1")), e -> {
            this.isFiltration = !this.isFiltration;
        });
        this.addRenderableWidget(filtration);
        this.titleLabelY -= 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor poseStack, int mouseX, int mouseY, float a) {
        super.extractBackground(poseStack, mouseX, mouseY, a);
        updateDisplay(mouseX,mouseY);
        int xo = this.leftPos;
        int yo = this.topPos;
        poseStack.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        if (isFiltration) {
            poseStack.blit(RenderPipelines.GUI_TEXTURED,FLOAT_PANEL,filtrationX, filtrationY, 0, 0, 63, 90,256, 256);
        }
        if (mouseX >= this.leftPos + 15 && mouseX <= this.leftPos + 38 && mouseY >= this.topPos + 30 && mouseY <= this.topPos + 53){
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 15, this.topPos + 30, 0, 190, 24, 24, 256, 256);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        double mouseX = event.x();
        double mouseY = event.y();
        if (isFiltration && isMouseOverFiltrationPanel(mouseX, mouseY)) {
            this.isDragged = true;
            this.dragOffsetX = (int)(mouseX - filtrationX);
            this.dragOffsetY = (int)(mouseY - filtrationY);
        }
        int x = filtrationX + 63 - 6;
        int y = filtrationY;
        if (mouseX >= x && mouseX <= filtrationX + 63 && mouseY >= y && mouseY <= y + 6){
            this.isFiltration = false;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        if (this.isDragged) {
            filtrationX = (int)(event.x() - dragOffsetX);
            filtrationY = (int)(event.y() - dragOffsetY);
        }
        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        this.pressTime = 0;
        this.isDragged = false;
        return super.mouseReleased(event);
    }

    private boolean isMouseOverFiltrationPanel(double mouseX, double mouseY) {
        return mouseX >= filtrationX && mouseX <= filtrationX + FILTRATION_WIDTH &&
                mouseY >= filtrationY && mouseY <= filtrationY + FILTRATION_HEIGHT;
    }

    private void updateDisplay(int mouseX, int mouseY) {
        if (isDragged) {
            isMove = true;
            filtrationX = mouseX - dragOffsetX;
            filtrationY = mouseY - dragOffsetY;
            filtrationX = Math.max(0, Math.min(filtrationX, width - 63));
            filtrationY = Math.max(0, Math.min(filtrationY, height - 90));
        }else if (!isMove){
            filtrationX = (width - imageWidth) / 2 - 63;
            filtrationY = (height - imageHeight) / 2;
        }
        for (int i = 0; i < 10; i++) {
            Slot slot = menu.getSlot(i + 11);
            if (slot instanceof BlenderMenu.FiltrationSlot filtrationSlot){
                filtrationSlot.setActive(isFiltration);
            }
        }
        if (!isFiltration) {
            return;
        }
        int slotBaseX = filtrationX - this.leftPos;
        int slotBaseY = filtrationY - this.topPos;
        int[][] slotOffsets = {{5, 8}, {23, 8}, {41, 8}, {5, 26}, {23, 26}, {41, 26}, {5, 44}, {23, 44}, {41, 44}, {23, 67}};

        for (int i = 0; i < 10; i++) {
            int slotIndex = 11 + i;
            if (slotIndex < this.menu.slots.size()) {
                int slotX = slotBaseX + slotOffsets[i][0];
                int slotY = slotBaseY + slotOffsets[i][1];
                this.menu.slots.get(slotIndex).x = slotX;
                this.menu.slots.get(slotIndex).y = slotY;
            }
        }
    }
}
