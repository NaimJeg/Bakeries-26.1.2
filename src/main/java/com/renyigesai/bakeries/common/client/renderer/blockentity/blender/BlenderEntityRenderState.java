package com.renyigesai.bakeries.common.client.renderer.blockentity.blender;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class BlenderEntityRenderState extends BlockEntityRenderState {
    public float open;
    public Direction facing;

    public BlenderEntityRenderState() {
        this.facing = Direction.DOWN;
    }
}
