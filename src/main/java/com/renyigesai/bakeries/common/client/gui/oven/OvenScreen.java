package com.renyigesai.bakeries.common.client.gui.oven;


import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlockEntity;
import com.renyigesai.bakeries.common.network.payload.OvenButtonPayload;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.client.ClientTooltipFlag;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OvenScreen extends AbstractContainerScreen<OvenMenu>{
    private final OvenBlockEntity ovenBlockEntity;
    public OvenScreen(OvenMenu ovenMenu, Inventory inventory, Component text) {
        super(ovenMenu, inventory, text);
        this.ovenBlockEntity = ovenMenu.getBlockEntity();
        this.titleLabelY = 4;//设置GUI标题高度

    }
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(BakeriesMod.MODID,"textures/gui/oven_gui.png");

    @Override
    public void extractBackground(GuiGraphicsExtractor poseStack, int mouseX, int mouseY, float a) {
        super.extractBackground(poseStack, mouseX, mouseY, a);
        int xo = this.leftPos;
        int yo = this.topPos;
        poseStack.pose().pushMatrix();
        poseStack.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        int x = this.leftPos;
        int y = this.topPos;
        Minecraft minecraft = Minecraft.getInstance();
        if (ovenBlockEntity != null){
            if (mouseX >= x + 125 && mouseX <= x + 132 && mouseY >= y + 16 && mouseY <= y + 62){
                List<Component> tooltip = List.of(
                        Component.translatable("container.bakeries.oven.temperature").withStyle(ChatFormatting.BLUE),
                        Component.literal(this.getMenu().data.get(0) + "°C").withStyle(ChatFormatting.WHITE),
                        Component.translatable("container.bakeries.oven.rolling").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC)
                );
                poseStack.setTooltipForNextFrame(minecraft.font,tooltip, Optional.empty(),mouseX,mouseY);
            }
            int progressH = (int) (38 * ((float) this.menu.data.get(0) / 500.0f));
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 128, this.topPos + (57 - progressH), 14, 166, 2, progressH, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 53, this.topPos + 36, 0, 166, this.menu.data.get(1), 2, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 71, this.topPos + 36, 0, 166, this.menu.data.get(2), 2, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 89, this.topPos + 36, 0, 166, this.menu.data.get(3), 2, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 53, this.topPos + 66, 0, 166, this.menu.data.get(4), 2, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 71, this.topPos + 66, 0, 166, this.menu.data.get(5), 2, 256, 256);
            poseStack.blit(RenderPipelines.GUI_TEXTURED,TEXTURE, this.leftPos + 89, this.topPos + 66, 0, 166, this.menu.data.get(6), 2, 256, 256);
        }
        poseStack.pose().popMatrix();

    }
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int x = this.leftPos;
        int y = this.topPos;
        if (mouseX >= x + 125 && mouseX <= x + 132 && mouseY >= y + 16 && mouseY <= y + 62){
            if (ovenBlockEntity != null) {
                int count = ClientTooltipFlag.of(TooltipFlag.Default.ADVANCED).hasShiftDown() ? 1 : 10;
                if(scrollY > 0){
                    ClientPacketDistributor.sendToServer(new OvenButtonPayload(OvenButtonPayload.ADD,ovenBlockEntity.getBlockPos(), count));
                }else if(scrollY < 0){
                    ClientPacketDistributor.sendToServer(new OvenButtonPayload(OvenButtonPayload.SUB,ovenBlockEntity.getBlockPos(), count));
                }
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_HAT, 2.0F));
            }
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX,scrollY);
    }

}
