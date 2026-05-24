package com.renyigesai.bakeries.common.client.renderer.blockentity.oven;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlock;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlockEntity;
import com.renyigesai.bakeries.common.client.model.OvenModel;
import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityItemRenderer;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class OvenRender extends BlockEntityItemRenderer<OvenBlockEntity,OvenRenderState> {
    private final OvenModel model;
    private final OvenModel glow;
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/oven/oven.png");
    public static final Identifier TEXTURE_LIT = ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/oven/oven_lit.png");
    public static final Identifier TEXTURE_GLOW =ResourceLocation.fromNamespaceAndPath("bakeries","textures/entity/oven/oven_glow.png");

    public static final float SUB = 0.03125f;
    private static final float[] SLOT_X_POSITIONS = {
            0.25f + SUB, 0.50f + SUB, 0.75f + SUB,  // 上层左、中、右
            0.25f + SUB, 0.50f + SUB, 0.75f + SUB   // 下层左、中、右
    };
    private static final float[] SLOT_Y_OFFSETS = {
            0.83125f, 0.83125f, 0.83125f,  // 上层高度
            0.5875f, 0.5875f, 0.5875f,  //   // 下层高度
    };
    private static final float SLOT_DEPTH = 0.5f;   // 现在物品位于方块前后方向的中心
    private static final float ITEM_SCALE = 0.65f;

    public OvenRender(BlockEntityRendererProvider.Context context) {
        super(context);
        this.model = new OvenModel(context.bakeLayer(OvenModel.OVEN));
        this.glow = model;
    }

    @Override
    public NonNullList<ItemStack> items(OvenBlockEntity oven) {
        return ItemUtils.toNonNullList(oven.getItemHandler());
    }

    @Override
    public void extract(OvenBlockEntity blockEntity, @NotNull OvenRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extract(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.open = blockEntity.getProgress(partialTicks);
        state.facing = blockEntity.getBlockState().getValue(OvenBlock.FACING).getOpposite();
        state.lit = blockEntity.getBlockState().getValue(OvenBlock.LIT);
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

        submitNodeCollector.submitModel(model,state.open,poseStack, state.lit ? TEXTURE_LIT : TEXTURE,state.lightCoords, OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        if (state.lit){
            submitNodeCollector.submitModel(glow,state.open,poseStack, RenderTypes.eyes(TEXTURE_GLOW),state.lightCoords, OverlayTexture.NO_OVERLAY,0,state.breakProgress);
        }
        poseStack.popPose();

        renderItem(itemStackRenderStates, blockModelRenderStates, state, submitNodeCollector, poseStack);
    }

    private void renderItem(ItemStackRenderState[] itemStackRenderStates, BlockModelRenderState[] blockModelRenderStates, OvenRenderState state, SubmitNodeCollector submitNodeCollector, PoseStack poseStack) {
        Direction facing = state.facing;
        float rotation = -facing.toYRot();

        for (int slot = 0; slot < 6; slot++) {
            ItemStackRenderState itemState = itemStackRenderStates[slot];
            BlockModelRenderState blockState = blockModelRenderStates[slot];
            if (itemState == null && blockState == null) continue;
            Vec2 localPos = new Vec2(SLOT_X_POSITIONS[slot], SLOT_DEPTH);
            Vec3 worldPos = transformPositionByDirection(localPos, facing);
            float yOffset = SLOT_Y_OFFSETS[slot];
            poseStack.pushPose();
            poseStack.translate(worldPos.x, yOffset, worldPos.z);
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
            poseStack.scale(ITEM_SCALE, ITEM_SCALE, ITEM_SCALE);
            itemState.submit(poseStack, submitNodeCollector,
                    state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    private Vec3 transformPositionByDirection(Vec2 position, Direction direction) {
        float x = position.x;
        float y = position.y; // 局部 x 右，y 上
        return switch (direction) {
            case NORTH -> new Vec3(x, 0, y);
            case SOUTH -> new Vec3(1 - x, 0, 1 - y);
            case EAST  -> new Vec3(1 - y, 0, x);
            case WEST  -> new Vec3(y, 0, 1 - x);
            default    -> new Vec3(x, 0, y);
        };
    }


    @Override
    public @NotNull OvenRenderState createRenderState() {
        return new OvenRenderState(6);
    }
}
