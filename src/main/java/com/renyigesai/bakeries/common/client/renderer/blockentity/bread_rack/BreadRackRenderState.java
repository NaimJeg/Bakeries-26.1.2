package com.renyigesai.bakeries.common.client.renderer.blockentity.bread_rack;

import com.renyigesai.bakeries.common.blocks.HorizontalConnectBlock;
import com.renyigesai.bakeries.common.client.renderer.blockentity.BlockEntityRenderItemState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

public class BreadRackRenderState extends BlockEntityRenderItemState {
    public Direction facing;
    public float open;
    public boolean isOpen;
    public HorizontalConnectBlock.Type type;
    public BreadRackRenderState(int count) {
        super(count);
        facing = Direction.NORTH;
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
}
