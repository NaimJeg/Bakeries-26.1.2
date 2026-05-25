package com.renyigesai.bakeries.common.blocks.menu;

import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import com.renyigesai.bakeries.common.utils.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("removal")
public class MenuBlockEntity extends BlockEntity implements ItemOwner {
    protected final ItemStackHandler inventory = new ItemStackHandler(1);
    public MenuBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.MENU_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        WorldUtils.loadItemsToHandler(input,this.inventory,"Items");
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        WorldUtils.saveAllItems(output, ItemUtils.toNonNullList(this.inventory),"Items");
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public void addItem(ItemStack stack, Level level,BlockPos pos,BlockState state){
        inventory.setStackInSlot(0,stack);
        this.setChanged();
        if (!level.isClientSide()){
            level.sendBlockUpdated(pos,state,state,3);
        }
    }

    public void deleteItem(Level level,BlockPos pos,BlockState state){
        this.inventory.setStackInSlot(0,ItemStack.EMPTY);
        this.setChanged();
        if (!level.isClientSide()){
            level.sendBlockUpdated(pos,state,state,3);
        }
    }

    @Override
    public Level level() {
        return level;
    }

    @Override
    public Vec3 position() {
        return null;
    }

    @Override
    public float getVisualRotationYInDegrees() {
        return 0;
    }
}
