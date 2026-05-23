package com.renyigesai.bakeries.common.blocks.mix_block;

import com.mojang.logging.LogUtils;
import com.renyigesai.bakeries.api.ItemStackHandler;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ListBackedContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

public class MixBlockEntity extends BlockEntity implements ListBackedContainer, ItemOwner {


    private ItemStackHandler inventory;
    private static final Logger LOGGER = LogUtils.getLogger();

    public MixBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.MIX_BLOCK_ENTITY.get(), pPos, pBlockState);
        inventory = new ItemStackHandler(4);
    }

    public int getInventoryCount() {
        int count = 0;
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()){
                count ++;
            }
        }
        return count;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventory.getItems();
    }

    public boolean isEmpty(){
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.problemPath(), LOGGER);

        CompoundTag var4;
        try {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
            ContainerHelper.saveAllItems(output, this.inventory.getItems(), true);
            var4 = output.buildResult();
        } catch (Throwable var7) {
            try {
                reporter.close();
            } catch (Throwable var6) {
                var7.addSuppressed(var6);
            }

            throw var7;
        }
        reporter.close();
        return var4;
    }


    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.inventory.getItems().clear();
        ContainerHelper.loadAllItems(input, this.inventory.getItems());
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory.getItems(), true);
    }

    public boolean addItem(ItemStack stack){
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stackInSlot = inventory.getStackInSlot(i);
            if (stackInSlot.isEmpty()){
                inventory.setStackInSlot(i,stack);
                updateBlock();
                return true;
            }
        }
        return false;
    }

    public Level level(){
        return this.level;
    }

    public Vec3 position() {
        return this.getBlockPos().getCenter();
    }

    public float getVisualRotationYInDegrees() {
        return ((Direction)this.getBlockState().getValue(MixBlock.FACING)).getOpposite().toYRot();
    }

    public void updateBlock() {
        if (level == null){
            return;
        }
        BlockState state = level.getBlockState(worldPosition);
        setChanged(level, worldPosition, state);
        level.sendBlockUpdated(worldPosition, state, state, 3);
    }
}
