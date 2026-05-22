package com.renyigesai.bakeries.common.client.renderer.blockentity.toaster;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlock;
import com.renyigesai.bakeries.common.blocks.toaster.ToasterBlockEntity;
import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityItemRenderer;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class ToasterRender extends BlockEntityItemRenderer<ToasterBlockEntity,ToasterRenderState> {
    private static final float SIZE = 0.03125F;
    private static final Vec2[] north = new Vec2[]{new Vec2(0.5f,0.625f - SIZE),new Vec2(0.5f,0.4375f - SIZE)};
    private static final Vec2[] south = new Vec2[]{new Vec2(0.5f,0.4375f - SIZE),new Vec2(0.5f,0.625f - SIZE)};
    private static final Vec2[] east = new Vec2[]{new Vec2(0.4375f - SIZE,0.5f),new Vec2(0.625f - SIZE,0.5f)};
    private static final Vec2[] west = new Vec2[]{new Vec2(0.625f - SIZE,0.5f),new Vec2(0.4375f - SIZE,0.5f)};
    public ToasterRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public NonNullList<ItemStack> items(ToasterBlockEntity blockEntity) {
        return ItemUtils.toNonNullList(blockEntity.getItems());
    }

    @Override
    public Level level(ToasterBlockEntity blockEntity) {
        return blockEntity.level();
    }

    @Override
    public void extract(ToasterBlockEntity blockEntity, @NonNull ToasterRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extract(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.facing = blockEntity.getBlockState().getValue(OvenBlock.FACING).getOpposite();
        state.momentumY = blockEntity.getProgress(partialTicks);
    }

    @Override
    public void render(ItemStackRenderState[] itemStackRenderStates, BlockModelRenderState[] blockModelRenderStates, ToasterRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        Direction direction = state.facing;
        for (int slot = 0; slot < itemStackRenderStates.length; slot++) {
            ItemStackRenderState itemStackRenderState = itemStackRenderStates[slot];
            if (itemStackRenderState != null){
                float f1 = -direction.toYRot() - 180f;
                Vec2[] vec2 = transformPositionByDirection(direction);
                poseStack.pushPose();
                poseStack.translate(vec2[slot].x,0.5 + (state.momentumY * 0.25),vec2[slot].y);
                poseStack.mulPose(Axis.YP.rotationDegrees(f1));
                poseStack.scale(0.5f, 0.5f, 0.5f);
                itemStackRenderState.submit(poseStack,submitNodeCollector,state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();
            }
        }
    }

    @Override
    public ToasterRenderState createRenderState() {
        return new ToasterRenderState(2);
    }

    private Vec2[] transformPositionByDirection(Direction direction) {
        return switch (direction){
            case NORTH -> north;
            case SOUTH -> south;
            case EAST -> east;
            case WEST -> west;
            default -> new Vec2[]{};
        };
    }
}
