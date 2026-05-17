package com.renyigesai.bakeries.common.client.renderer.blockentity.oven;

import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityRenderItemState;
import net.minecraft.core.Direction;

public class OvenRenderState extends BlockEntityRenderItemState {
    public float open;
    public Direction facing;
    public OvenRenderState(int count) {
        super(count);
        this.facing = Direction.NORTH;
    }
}
