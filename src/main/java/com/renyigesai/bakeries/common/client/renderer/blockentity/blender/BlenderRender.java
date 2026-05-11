package com.renyigesai.bakeries.common.client.renderer.blockentity.blender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlockEntity;
import com.renyigesai.bakeries.common.client.model.BlenderModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.MultiblockChestResources;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class BlenderRender implements BlockEntityRenderer<BlenderBlockEntity,BlenderEntityRenderState> {
    private final BlenderModel model;
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/blender.png");

    public BlenderRender(BlockEntityRendererProvider.Context context) {
        this.model = new BlenderModel(context.bakeLayer(BlenderModel.BLENDER));
    }

    @Override
    public BlenderEntityRenderState createRenderState() {
        return new BlenderEntityRenderState();
    }

    @Override
    public void extractRenderState(BlenderBlockEntity blockEntity, BlenderEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.open = blockEntity.getProgress(partialTicks);
        state.facing = blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
    }

    //渲染方块模型
    @Override
    public void submit(BlenderEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        //基础变换，无脑抄
        poseStack.translate(0.5,1.5,0.5);
        poseStack.mulPose(Axis.XP.rotationDegrees(180F));
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.facing.toYRot()));

        //设置动画，现在需要在模型里调用动画
        model.setupAnim(state.open);

        /*渲染方块模型，参数model是你做好的Java模型，state值视情况传递，poseStack就填poseStack，texture是贴图，返回资源地址即可
        * lightCoords从state获取lightCoords即可，overlayCoords直接填OverlayTexture.NO_OVERLAY,outlineColor填0，最后一个直接从
        * state获取breakProgress
        * */
        submitNodeCollector.submitModel(model,state.open,poseStack, TEXTURE,state.lightCoords,OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        poseStack.popPose();
    }

}
