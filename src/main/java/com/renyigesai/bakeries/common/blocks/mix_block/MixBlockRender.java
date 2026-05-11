package com.renyigesai.bakeries.common.blocks.mix_block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.HashCommon;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class MixBlockRender implements BlockEntityRenderer<MixBlockEntity, MixBlockRenderState> {

    public static final float ADD_SIZE = 0.25f;
    public static final Vec2[][] VEC2S = {
            new Vec2[]{},
            new Vec2[]{new Vec2(0.5f, 0.5f)},
            new Vec2[]{new Vec2(0.5f - ADD_SIZE, 0.5f), new Vec2(0.5f + ADD_SIZE, 0.5f)},
            new Vec2[]{new Vec2(0.5f - ADD_SIZE, 0.5f - ADD_SIZE), new Vec2(0.5f + ADD_SIZE, 0.5f - ADD_SIZE), new Vec2(0.5f, 0.5f + ADD_SIZE)},
            new Vec2[]{new Vec2(0.5f - ADD_SIZE, 0.5f - ADD_SIZE), new Vec2(0.5f + ADD_SIZE, 0.5f - ADD_SIZE), new Vec2(0.5f - ADD_SIZE, 0.5f + ADD_SIZE), new Vec2(0.5f + ADD_SIZE, 0.5f + ADD_SIZE)}
    };

    private final ItemModelResolver itemModelResolver;
    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public MixBlockRender(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public MixBlockRenderState createRenderState() {
        return new MixBlockRenderState();
    }

    @Override
    public void extractRenderState(MixBlockEntity blockEntity, MixBlockRenderState renderState, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTicks, cameraPosition, breakProgress);

        renderState.facing = blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        renderState.tray = blockEntity.getBlockState().getValue(MixBlock.TRAY);
        NonNullList<ItemStack> items = blockEntity.getInventory().getItems();
        int seed = HashCommon.long2int(blockEntity.getBlockPos().asLong());
        for(int slot = 0; slot < items.size(); ++slot) {
            ItemStack itemStack = items.get(slot);
            if (!itemStack.isEmpty()) {
                ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
                BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
                this.itemModelResolver.updateForTopItem(itemStackRenderState, itemStack, ItemDisplayContext.ON_SHELF, blockEntity.level(), blockEntity, seed + slot);
                renderState.items[slot] = itemStackRenderState;
                if (itemStack.getItem() instanceof BlockItem blockItem){
                    this.blockModelResolver.update(blockModelRenderState, blockItem.getBlock().defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
                    renderState.blockStates[slot] = blockModelRenderState;
                }
            }
        }
        renderState.blockPos = blockEntity.getBlockPos();
    }

    @Override
    public void submit(MixBlockRenderState renderState, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {

        for(int slot = 0; slot < renderState.items.length; ++slot) {
            ItemStackRenderState itemStackRenderState = renderState.items[slot];
            BlockModelRenderState blockModelRenderState = renderState.blockStates[slot];
            if (blockModelRenderState != null){
                this.submitBlockItem(renderState,poseStack,collector,slot);
            }else if (itemStackRenderState != null){
                this.submitItem(renderState, itemStackRenderState, poseStack, collector, slot);
            }
        }
    }

    private void submitItem(MixBlockRenderState state, ItemStackRenderState itemStackRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int slot) {
        if (!(state.getInventoryCount() >= 1)){
            return;
        }
        Vec2[] positions = VEC2S[state.getInventoryCount()];
        float rotation = -state.facing.toYRot();
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.0F);
//        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
//        poseStack.mulPose(Axis.XP.rotationDegrees(0));
        poseStack.translate(transformPositionByDirection(positions[slot],state.facing));
//        poseStack.mulPose(Axis.YP.rotationDegrees(15));
        float size = 1.0f;
        poseStack.scale(size, size, size);
        itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    private void submitBlockItem(MixBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int slot) {
        if (!(state.getInventoryCount() >= 1)){
            return;
        }
        Vec2[] positions = VEC2S[state.getInventoryCount()];
        float rotation = -state.facing.toYRot();
        poseStack.pushPose();

        poseStack.translate(0.5F,0.0F,0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation + 15));
        poseStack.mulPose(Axis.XP.rotationDegrees(0));
        float size = 1.0f;
        poseStack.scale(size, size, size);
        Vec3 vec3 = transitionVec3(positions[slot]);
        poseStack.translate(-vec3.x,0.0,-vec3.z);
        state.blockStates[slot].submitWithZOffset(poseStack,submitNodeCollector,state.lightCoords,OverlayTexture.NO_OVERLAY,0);
        poseStack.popPose();
    }

    private Vec3 transformPositionByDirection(Vec2 position, Direction direction) {
        float x = position.x;
        float y = position.y;
        return switch (direction) {
            case NORTH -> new Vec3(x, 0, y);
            case SOUTH -> new Vec3(1 - x, 0, 1 - y);
            case EAST  -> new Vec3(1 - y, 0, x);
            case WEST  -> new Vec3(y, 0, 1 - x);
            default   -> new Vec3(x, 0, y);
        };
    }

    private Vec3 transitionVec3(Vec2 position) {
        float x = position.x;
        float z = position.y;
        return new Vec3(x,0,z);
    }
}