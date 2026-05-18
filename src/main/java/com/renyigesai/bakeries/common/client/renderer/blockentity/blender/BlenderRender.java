package com.renyigesai.bakeries.common.client.renderer.blockentity.blender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlock;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlockEntity;
import com.renyigesai.bakeries.common.client.model.BlenderModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
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
        state.open[0] = blockEntity.getProgress(partialTicks);
        state.open[1] = blockEntity.getRprogress(partialTicks);
        state.facing = blockEntity.getBlockState().getValue(BlenderBlock.FACING).getOpposite();
    }

    //��Ⱦ����ģ��
    @Override
    public void submit(BlenderEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        //�����任�����Գ�
        poseStack.translate(0.5,1.5,0.5);
        poseStack.mulPose(Axis.XP.rotationDegrees(180F));
        poseStack.mulPose(Axis.YP.rotationDegrees(state.facing.toYRot()));
        poseStack.scale(0.9995F, 0.9995F, 0.9995F);

//        model.getUp().xRot = (float) Math.toRadians(state.open * -25);
//        model.getUp().xRot = -(state.open * ((float)Math.PI / 2F));

        /*��Ⱦ����ģ�ͣ�����model�������õ�Javaģ�ͣ�stateֵ��������ݣ�poseStack����poseStack��texture����ͼ��������Դ��ַ����
        * lightCoords��state��ȡlightCoords���ɣ�overlayCoordsֱ����OverlayTexture.NO_OVERLAY,outlineColor��0�����һ��ֱ�Ӵ�
        * state��ȡbreakProgress
        * */
        submitNodeCollector.submitModel(model,state.open,poseStack, TEXTURE,state.lightCoords,OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        poseStack.popPose();
    }

}
