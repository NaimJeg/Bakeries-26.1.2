package com.renyigesai.bakeries.common.blocks.moka_pot;

import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import com.renyigesai.bakeries.common.utils.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("removal")
public class MokaPotBlockEntity extends BlockEntity {
    protected ItemStackHandler inventory = new ItemStackHandler(1);//11个槽位
    public int cookingTotalTime;
    public boolean fill;
    public long wobbleStartedAtTick;
    public MokaPotBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.MOKA_POT_ENTITY.get(), pPos, pBlockState);
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
        this.inventory = new ItemStackHandler(1);
        WorldUtils.loadItemsToHandler(input,this.inventory,"Inventory");

        cookingTotalTime = input.getIntOr("CookingTotalTime",0);
        fill = input.getBooleanOr("Fill",false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        WorldUtils.saveAllItems(output, ItemUtils.toNonNullList(this.inventory),"Inventory");
        output.putInt("CookingTotalTime",cookingTotalTime);
        output.putBoolean("Fill",fill);
    }

    public boolean isEmpty(){
        return inventory.getStackInSlot(0).isEmpty();
    }

    public int getCookingTotalTime() {
        return cookingTotalTime;
    }

    public boolean getFill(){
        return fill;
    }

    public void addGroundCoffee(ItemStack stack){
        inventory.setStackInSlot(0,stack);
    }

    public boolean isCraft(Level level,BlockPos pos){
        BlockState state = level.getBlockState(pos.below());
        return state.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty booleanProperty && state.getValue(booleanProperty);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 0) {
            if (type == 0 && this.level != null) {
                this.wobbleStartedAtTick = this.level.getGameTime();
            }
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    public static void craftTick(Level level, BlockPos pos, BlockState state, MokaPotBlockEntity blockEntity) {
        if (blockEntity.isCraft(level, pos) && !blockEntity.isEmpty()) {
            blockEntity.tick();
            setChanged(level, pos, state);
            if (!level.isClientSide()) {
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    public void tick(){
        if (inventory.getStackInSlot(0).is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("c","coffee_grounds")))){
            if (cookingTotalTime < 200){
                ++ cookingTotalTime;
                if (cookingTotalTime % 10 == 0){
                    if (level != null && !level.isClientSide()) {
                        level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 0, 0);
                    }
                }
            }else {
                inventory.extractItem(0,1,false);
                cookingTotalTime = 0;
                fill = true;
            }
        }
    }

    public ItemStack extractGroundCoffee(){
        // Extract item from slot 0
        return inventory.extractItem(0, 64, false);
    }
}
