package com.renyigesai.bakeries.common.client.renderer.blockentity.oven;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlock;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlockEntity;
import com.renyigesai.bakeries.common.client.model.OvenModel;
import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityItemRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("removal")
public class OvenRender extends BlockEntityItemRenderer<OvenBlockEntity,OvenRenderState> {
    private final OvenModel model;
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/oven/oven.png");
    public OvenRender(BlockEntityRendererProvider.Context context) {
        super(context);
        this.model = new OvenModel(context.bakeLayer(OvenModel.OVEN));
    }

    @Override
    public NonNullList<ItemStack> items(OvenBlockEntity oven) {
        ItemStackHandler itemHandler = oven.getItemHandler();
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(itemHandler.getSlots(),ItemStack.EMPTY);
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            nonNullList.set(i,itemHandler.getStackInSlot(i));
        }
        return nonNullList;
    }

    @Override
    public void extract(OvenBlockEntity blockEntity, @NotNull OvenRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extract(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.open = blockEntity.getProgress(partialTicks);
        state.facing = blockEntity.getBlockState().getValue(OvenBlock.FACING).getOpposite();
    }

    @Override
    public Level level(OvenBlockEntity blockEntity) {
        return blockEntity.level();
    }

    @Override
    public void render(ItemStackRenderState[] itemStackRenderStates, BlockModelRenderState[] blockModelRenderStates, OvenRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        //基础变换，无脑抄
        poseStack.translate(0.5,1.5,0.5);
        poseStack.mulPose(Axis.XP.rotationDegrees(180F));
        poseStack.mulPose(Axis.YP.rotationDegrees(state.facing.toYRot()));
        poseStack.scale(0.9995F, 0.9995F, 0.9995F);

//        model.getDoor().xRot = (float) Math.toRadians(state.open * -25);

        /*渲染方块模型，参数model是你做好的Java模型，state值视情况传递，poseStack就填poseStack，texture是贴图，返回资源地址即可
         * lightCoords从state获取lightCoords即可，overlayCoords直接填OverlayTexture.NO_OVERLAY,outlineColor填0，最后一个直接从
         * state获取breakProgress
         * */
        submitNodeCollector.submitModel(model,state.open,poseStack, TEXTURE,state.lightCoords, OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        poseStack.popPose();
    }

    @Override
    public @NotNull OvenRenderState createRenderState() {
        return new OvenRenderState(6);
    }
}
