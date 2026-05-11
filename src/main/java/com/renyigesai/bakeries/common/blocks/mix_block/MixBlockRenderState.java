package com.renyigesai.bakeries.common.blocks.mix_block;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MixBlockRenderState extends BlockEntityRenderState {
    public final @Nullable ItemStackRenderState[] items = new ItemStackRenderState[4];
    public final BlockModelRenderState[] blockStates = new BlockModelRenderState[4];
    public final List<Integer> nonEmptySlots = new ArrayList<>();
    public Direction facing;
    public Boolean tray;

    public MixBlockRenderState() {
        this.facing = Direction.NORTH;
    }

    public boolean isEmpty(){
        for (ItemStackRenderState itemStackRenderState : items) {
            if (itemStackRenderState != null) {
                return false;
            }
        }
        return true;
    }

    public int getInventoryCount() {
        int count = 0;
        for (ItemStackRenderState itemStackRenderState : items) {
            if (itemStackRenderState != null) {
                count++;
            }
        }
        return count;
    }

    public boolean isTray(){
        return tray;
    }
}
