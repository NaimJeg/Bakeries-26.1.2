package com.renyigesai.bakeries.common.client.renderer.blockentity.menu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.bakeries.common.blocks.menu.MenuBlock;
import com.renyigesai.bakeries.common.blocks.menu.MenuBlockEntity;
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
import org.jetbrains.annotations.NotNull;

public class MenuRender extends BlockEntityItemRenderer<MenuBlockEntity,MenuRenderState> {

    private static final float SIZE = 0.0f;
    private static final Vec2 V_NORTH = new Vec2(0.5f,0.9375f - SIZE);
    private static final Vec2 V_SOUTH = new Vec2(0.5f,0.0625f - SIZE);
    private static final Vec2 V_EAST = new Vec2(0.0625f - SIZE,0.5f);
    private static final Vec2 V_WEST = new Vec2(0.9375f - SIZE,0.5f);

    public MenuRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public NonNullList<ItemStack> items(MenuBlockEntity blockEntity) {
        return ItemUtils.toNonNullList(blockEntity.getInventory());
    }

    @Override
    public Level level(MenuBlockEntity blockEntity) {
        return blockEntity.getLevel();
    }

    @Override
    public void extract(MenuBlockEntity blockEntity, @NotNull MenuRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extract(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.direction = blockEntity.getBlockState().getValue(MenuBlock.FACING);
    }

    @Override
    public void render(ItemStackRenderState[] itemStackRenderStates, BlockModelRenderState[] blockModelRenderStates, MenuRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        Direction direction = state.direction.getOpposite();
        ItemStackRenderState stackInSlot = itemStackRenderStates[0];
        if (stackInSlot != null){
            Vec2 vec2 = transformPositionByDirection(direction);
            float f1 = -direction.toYRot() - 180f;
            poseStack.pushPose();
            poseStack.translate(vec2.x,0.3125,vec2.y);
            float scale = 0.55f;
            poseStack.scale(scale,scale,scale);
            poseStack.mulPose(Axis.YP.rotationDegrees(f1));
            stackInSlot.submit(poseStack,submitNodeCollector,state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    @Override
    public @NotNull MenuRenderState createRenderState() {
        return new MenuRenderState(1);
    }

    private Vec2 transformPositionByDirection(Direction direction) {
        return switch (direction){
            case NORTH -> V_NORTH;
            case SOUTH -> V_SOUTH;
            case EAST -> V_EAST;
            case WEST -> V_WEST;
            default -> new Vec2(0.0f,0.0f);
        };
    }
}
