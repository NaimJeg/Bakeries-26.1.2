package com.renyigesai.bakeries.common.client.renderer.blockentity.moka_pot;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.moka_pot.MokaPotBlock;
import com.renyigesai.bakeries.common.blocks.moka_pot.MokaPotBlockEntity;
import com.renyigesai.bakeries.common.client.model.MokaPotModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

public class MokaPotRender implements BlockEntityRenderer<MokaPotBlockEntity,MokaPotRenderState> {
    public final MokaPotModel model;
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/moka_pot.png");

    public MokaPotRender(BlockEntityRendererProvider.Context context) {
        this.model = new MokaPotModel(context.bakeLayer(MokaPotModel.LAYER_LOCATION));
    }


    @Override
    public void extractRenderState(MokaPotBlockEntity blockEntity, MokaPotRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.direction = blockEntity.getBlockState().getValue(MokaPotBlock.FACING).getOpposite();
        state.wobbleStartedAtTick = blockEntity.wobbleStartedAtTick;
        state.level = blockEntity.getLevel();
        state.partialTick = partialTicks;
    }

    @Override
    public MokaPotRenderState createRenderState() {
        return new MokaPotRenderState();
    }

    @Override
    public void submit(MokaPotRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        long l = state.level.getGameTime() - state.wobbleStartedAtTick;
        if (l < 10) {
            float decay = 1.0f;
            float enhancedDecay = (float)Math.pow(decay, 1.2f);
            float rotTime = (l + state.partialTick) * 0.45f;
            float shakeAngle = enhancedDecay * (float)Math.sin(rotTime * Math.PI * 2) * 8.0f;
            float xTime = (l + state.partialTick) * 0.38f;
            float xOffset = enhancedDecay * (float)Math.cos(xTime * Math.PI * 2 + 0.7f) * 0.03125f;
            float zTime = (l + state.partialTick) * 0.42f;
            float zOffset = enhancedDecay * (float)Math.sin(zTime * Math.PI * 2 + 1.5f) * 0.03125f;
            poseStack.translate(0.5F + xOffset, 1.5F, 0.5F + zOffset);
            poseStack.mulPose(Axis.YP.rotationDegrees(-state.direction.toYRot()));
            poseStack.mulPose(Axis.XP.rotationDegrees(180F));
            poseStack.scale(0.9995F, 0.9995F, 0.9995F);
//            this.model.getAll().yRot = (float) Math.toRadians(shakeAngle);
        } else {
            poseStack.translate(0.5,1.5,0.5);
            poseStack.mulPose(Axis.XP.rotationDegrees(180F));
            poseStack.mulPose(Axis.YP.rotationDegrees(state.direction.toYRot()));
            poseStack.scale(0.9995F, 0.9995F, 0.9995F);
//            this.model.getAll().yRot = 0.0f;
        }
        submitNodeCollector.submitModel(model,state.wobbleStartedAtTick,poseStack, TEXTURE,state.lightCoords, OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        poseStack.popPose();
    }
}
