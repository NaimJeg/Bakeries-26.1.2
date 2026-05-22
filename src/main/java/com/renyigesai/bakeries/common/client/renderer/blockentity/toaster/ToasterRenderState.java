package com.renyigesai.bakeries.common.client.renderer.blockentity.toaster;

import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityRenderItemState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

public class ToasterRenderState extends BlockEntityRenderItemState {
    public Direction facing;
    public float momentumY;
    public ToasterRenderState(int count) {
        super(count);
        facing = Direction.DOWN;
    }
}
