package com.renyigesai.bakeries.common.client.renderer.blockentity.moka_pot;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class MokaPotRenderState extends BlockEntityRenderState {
    public Direction direction;
    public long wobbleStartedAtTick;
    public Level level;
    public float partialTick;

    public MokaPotRenderState() {
        this.direction = Direction.NORTH;
    }
}
