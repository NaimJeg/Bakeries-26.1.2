package com.renyigesai.bakeries.common.client.renderer.blockentity.luminous_light_sign;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class LuminousLightSignRenderState extends BlockEntityRenderState {
    public Direction facing;
    public String text;
    public int color;

    public LuminousLightSignRenderState() {
        this.facing = Direction.NORTH;
    }
}
