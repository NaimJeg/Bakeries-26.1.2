package com.renyigesai.bakeries.common.blocks.dough_crafting_table;

import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

public class DoughCraftingTableBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27,ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        }

        @Override
        protected void onClose(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        }

        @Override
        protected void openerCountChanged(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, int pCount, int pOpenCount) {

        }

        @Override
        public boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu){
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                return container == DoughCraftingTableBlockEntity.this;
            }else {
                return false;
            }
        }
    };

    public DoughCraftingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.DOUGH_CRAFTING_TABLE_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {return this.items;}

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> pItemStacks) {this.items = pItemStacks;}

    @Override
    protected @NotNull Component getDefaultName() {return Component.translatable("container.bakeries.dough_crafting_table");}

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory) {
        return ChestMenu.threeRows(pContainerId, pInventory,this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!this.trySaveLootTable(output)) {
            ContainerHelper.saveAllItems(output,this.items);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        this.items = NonNullList.withSize(this.getContainerSize(),ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) {
            ContainerHelper.loadAllItems(input,this.items);
        }
    }

    @Override
    public void startOpen(ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            this.openersCounter
                    .incrementOpeners(
                            containerUser.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState(), containerUser.getContainerInteractionRange()
                    );
        }
    }

    @Override
    public void stopOpen(ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            this.openersCounter.decrementOpeners(containerUser.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
}
