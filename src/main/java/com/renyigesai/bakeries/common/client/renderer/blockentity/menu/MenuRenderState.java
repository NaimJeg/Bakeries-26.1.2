package com.renyigesai.bakeries.common.client.renderer.blockentity.menu;

import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityRenderItemState;
import net.minecraft.core.Direction;

public class MenuRenderState extends BlockEntityRenderItemState {
    public Direction direction;
    public MenuRenderState(int count) {
        super(count);
        this.direction = Direction.NORTH;
    }
}
