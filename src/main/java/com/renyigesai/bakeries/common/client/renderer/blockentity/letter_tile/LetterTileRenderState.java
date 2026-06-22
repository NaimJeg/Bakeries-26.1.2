package com.renyigesai.bakeries.common.client.renderer.blockentity.letter_tile;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class LetterTileRenderState extends BlockEntityRenderState {
    public Direction facing;
    public String text;
    public int color;

    public LetterTileRenderState() {
        this.facing = Direction.NORTH;
    }
}
