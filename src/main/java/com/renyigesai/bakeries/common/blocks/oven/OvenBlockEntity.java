package com.renyigesai.bakeries.common.blocks.oven;


import com.renyigesai.bakeries.common.client.gui.oven.OvenMenu;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.init.BakeriesDataComponents;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
import com.renyigesai.bakeries.common.recipe.blender.BlenderInput;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import com.renyigesai.bakeries.common.utils.WorldUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("removal")
public class OvenBlockEntity extends BlockEntity implements Container, MenuProvider, ItemOwner {
    private ItemStackHandler itemHandler = new ItemStackHandler(6){
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }
    };
//    public final Component name = Component.translatable(UtilTranslatable.setContainer(BakeriesMod.MODID, "oven"));
    private Optional<IItemHandler> optionalIItemHandler;
    public final int[] cooking_times = new int[6];
    public final int[] max_cooking_times = new int[6];
    private final int[] min_temperatures = new int[6];
    private final int[] max_temperatures = new int[6];
    public int temperature;
    private boolean newVersion = false;

    public final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> OvenBlockEntity.this.temperature;
                case 1 -> calculateProgress(0);
                case 2 -> calculateProgress(1);
                case 3 -> calculateProgress(2);
                case 4 -> calculateProgress(3);
                case 5 -> calculateProgress(4);
                case 6 -> calculateProgress(5);
                default -> 0;
            };
        }

        private int calculateProgress(int index) {
            if (OvenBlockEntity.this.max_cooking_times[index] == 0) {
                return 0;
            }
            return Math.min((int) ((OvenBlockEntity.this.cooking_times[index] / (double) OvenBlockEntity.this.max_cooking_times[index]) * 14), 14);
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 0) {
                OvenBlockEntity.this.temperature = pValue;
            }
        }

        @Override
        public int getCount() {
            return 7;
        }
    };
    private final RecipeManager.CachedCheck<SingleRecipeInput, OvenRecipe> quickCheck;

    public State state = State.CLOSE;
    public float progress;
    public float progressOld;


    public OvenBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.OVEN_ENTITY.get(), pPos, pBlockState);
        optionalIItemHandler = Optional.empty();
        quickCheck = RecipeManager.createCheck(BakeriesRecipes.OVEN_TYPE.get());
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }
    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("");
    }
    //
    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player player) {
        return new OvenMenu(pContainerId, pInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition),this, this.dataAccess);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        WorldUtils.saveAllItems(output, ItemUtils.toNonNullList(this.itemHandler),"Items");
        output.putIntArray("cooking_times", this.cooking_times);
        output.putIntArray("max_cooking_times", this.max_cooking_times);
        output.putIntArray("min_temperatures", this.min_temperatures);
        output.putIntArray("max_temperatures", this.max_temperatures);
        output.putInt("temperature", this.temperature);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        this.itemHandler = new ItemStackHandler(6);
        WorldUtils.loadItemsToHandler(input,this.itemHandler,"Items");

        Optional<int[]> aint1 = input.getIntArray("CookingTimes");
        aint1.ifPresent(ints -> System.arraycopy(ints, 0, this.cooking_times, 0, Math.min(this.max_cooking_times.length, ints.length)));

        Optional<int[]> aint2 = input.getIntArray("MinTemperatures");
        aint2.ifPresent(ints -> System.arraycopy(ints, 0, this.min_temperatures, 0, Math.min(this.min_temperatures.length, ints.length)));

        Optional<int[]> aint3 = input.getIntArray("MaxTemperatures");
        aint3.ifPresent(ints -> System.arraycopy(ints, 0, this.max_temperatures, 0, Math.min(this.max_temperatures.length, ints.length)));

        if (input.getInt("temperature").isPresent()){
            this.temperature = input.getInt("temperature").get();
        }

    }

    @Override
    public void onLoad() {
        super.onLoad();
        optionalIItemHandler = Optional.of(itemHandler);
    }

    @Override
    public void invalidateCapabilities() {
        super.invalidateCapabilities();
        optionalIItemHandler = Optional.empty();

    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    public float getProgress(float pPartialTicks) {
        return Mth.lerp(pPartialTicks, this.progressOld, this.progress);
    }

    @Override
    public boolean triggerEvent(int pId, int pType) {
        if (pId == 0) {
            if (pType == 0) {
                this.state = State.OPEN_PROCESS;
            }
            if (pType == 1) {
                this.state = State.CLOSE_PROCESS;
            }
            doNeighborUpdates(this.getLevel(), this.worldPosition, this.getBlockState());
            return true;
        } else {
            return super.triggerEvent(pId, pType);
        }
    }

    private static void doNeighborUpdates(Level pLevel, BlockPos pPos, BlockState pState) {
        pState.updateNeighbourShapes(pLevel, pPos, 3);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, OvenBlockEntity blockEntity){
        blockEntity.progressOld = blockEntity.progress;
        if (!state.getValue(OvenBlock.LIT)) {
            switch (blockEntity.state) {
                case OPEN_PROCESS:
                    blockEntity.progress += 0.25F;
                    if (blockEntity.progress >= 1.0F) {
                        blockEntity.progress = 1.0F;
                        blockEntity.state = State.OPEN;
                    }
                    break;
                case OPEN:
                    blockEntity.progress = 1.0F;
                    break;
                case CLOSE_PROCESS:
                    blockEntity.progress -= 0.25F;
                    if (blockEntity.progress <= 0F) {
                        blockEntity.progress = 0F;
                        blockEntity.state = State.CLOSE;
                    }
                    break;
                case CLOSE:
                    blockEntity.progress = 0.0F;
                    break;
            }
        }else {
            if (blockEntity.progress > 0F){
                blockEntity.progress -= 0.25F;
            }
            if (blockEntity.progress <= 0){
                blockEntity.progress = 0F;
            }
            blockEntity.state = State.CLOSE;
        }
    }

    private boolean hasInput() {
        for(int i = 0; i < itemHandler.getSlots(); ++i) {
            if (!this.itemHandler.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, OvenBlockEntity pOvenBlockEntity) {
        if (pOvenBlockEntity.hasInput()){
            for (int i = 0; i < pOvenBlockEntity.itemHandler.getSlots(); i++) {
                if (!pOvenBlockEntity.itemHandler.getStackInSlot(i).isEmpty()) {
                    recipeItem(pLevel, pPos, pState, i, pOvenBlockEntity);
                }
            }
            setFire(pLevel, pPos, pState, pOvenBlockEntity);
            setChanged(pLevel, pPos, pState);
            updateBlock(pOvenBlockEntity);
        }

    }

    public static void setFire(Level world, BlockPos pos, BlockState state, OvenBlockEntity pOvenBlockEntity) {
        boolean isLit = pOvenBlockEntity.cooking_times[0] > 0 || pOvenBlockEntity.cooking_times[1] > 0 || pOvenBlockEntity.cooking_times[2] > 0 || pOvenBlockEntity.cooking_times[3] > 0 || pOvenBlockEntity.cooking_times[4] > 0 || pOvenBlockEntity.cooking_times[5] > 0;
        world.setBlock(pos, pOvenBlockEntity.getBlockState().setValue(OvenBlock.LIT, isLit), 3);
    }


    public void setTemperature(int temperature) {
        this.temperature = Math.clamp(temperature, 0, 500);
        updateBlock(this);
    }

    public void addTemperature(int temperature) {
        this.temperature = Math.min(Math.max(this.getTemperature() + temperature, 0), 500);
        updateBlock(this);
    }

    public void subTemperature(int temperature) {
        this.temperature = Math.min(Math.max(this.getTemperature() - temperature, 0), 500);
        updateBlock(this);
    }


    public static void updateBlock(OvenBlockEntity ovenBlockEntity) {
        Level world = ovenBlockEntity.getLevel();
        BlockPos pos = ovenBlockEntity.getBlockPos();
        BlockState state = world.getBlockState(pos);
        setChanged(world, pos, state);
        world.sendBlockUpdated(pos, state, state, 3);
    }

    private static void recipeItem(Level world, BlockPos pos, BlockState state, int slot, OvenBlockEntity ovenBlockEntity) {
        Optional<RecipeHolder<OvenRecipe>> recipe = ovenBlockEntity.getCurrentRecipe(slot);
        int temperature = ovenBlockEntity.temperature;
        recipe.ifPresent(ovenRecipe -> {
            OvenRecipe ovenRecipeValue = ovenRecipe.value();
            ovenBlockEntity.max_cooking_times[slot] = ovenRecipeValue.getCookingTime();
            ovenBlockEntity.min_temperatures[slot] = Math.max(ovenRecipeValue.getMinTemperature(), 0);
            ovenBlockEntity.max_temperatures[slot] = Math.min(ovenRecipeValue.getMaxTemperature(), 500);
        });
        if (ovenBlockEntity.hasRecipe(slot) && recipe.isPresent() && Math.max(ovenBlockEntity.min_temperatures[slot], 0) <= temperature) {
            OvenRecipe recipeValue = recipe.get().value();

            if (!world.isClientSide()) {
                int cookingTime = ovenBlockEntity.cooking_times[slot]++;
                int maxCookingTime = ovenBlockEntity.max_cooking_times[slot];

                int craftTemperature = Math.min(ovenBlockEntity.max_temperatures[slot], 500);

                if (cookingTime >= maxCookingTime) {
                    if (temperature <= craftTemperature) {
                        boolean perfect = recipeValue.isPresentPerfect() && temperature == recipeValue.getPerfectTemperature();
                        ovenBlockEntity.craftItem(ovenBlockEntity, slot, perfect);
                    } else {
                        ovenBlockEntity.itemHandler.setStackInSlot(slot, new ItemStack(Items.CHARCOAL, 1));
                    }
                    world.sendBlockUpdated(pos, state, state, 3);
                    world.playSound(null, pos, SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    resetProgress(ovenBlockEntity, slot);
                }
            }
        } else {
            if (!world.isClientSide()) {
                world.sendBlockUpdated(pos, state, state, 3);
                resetProgress(ovenBlockEntity, slot);
            }
        }
    }

    private static void resetProgress(OvenBlockEntity ovenBlockEntity, int slot) {
        ovenBlockEntity.max_cooking_times[slot] = 0;
        ovenBlockEntity.cooking_times[slot] = 0;
        ovenBlockEntity.min_temperatures[slot] = 0;
        ovenBlockEntity.max_temperatures[slot] = 0;
    }
    private void craftItem(OvenBlockEntity ovenBlockEntity, int slot, boolean perfect) {
        updateBlock(ovenBlockEntity);
        Optional<RecipeHolder<OvenRecipe>> recipe = getCurrentRecipe(slot);
        if (recipe.isPresent()) {
            OvenRecipe recipeValue = recipe.get().value();
            ItemStack result = recipeValue.getResult().create();
            ItemStack takeItem = new ItemStack(result.getItem(), result.getCount());
            if (perfect){
                takeItem.set(BakeriesDataComponents.PERFECT.get(), true);
            }
            this.itemHandler.setStackInSlot(slot, takeItem);
            updateBlock(ovenBlockEntity);
        }
    }

    private boolean hasRecipe(int slot) {
        Optional<RecipeHolder<OvenRecipe>> recipe = getCurrentRecipe(slot);
        return recipe.isPresent() && recipe.get().value().getInput().test(itemHandler.getStackInSlot(slot));
    }

    public Optional<RecipeHolder<OvenRecipe>> getCurrentRecipe(int slot) {
        if (this.level instanceof ServerLevel){
            return quickCheck.getRecipeFor(new SingleRecipeInput(this.itemHandler.getStackInSlot(slot)), (ServerLevel) level);
        }else {
            return Optional.empty();
        }
    }
    @Override
    public int getContainerSize() {
        return 6;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            if (!this.itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return this.itemHandler.getStackInSlot(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        return removeItem(this.itemHandler, pSlot, pAmount);
    }
    public static ItemStack removeItem(ItemStackHandler itemHandler, int pIndex, int pAmount) {
        return pIndex >= 0 && pIndex < itemHandler.getSlots() && !itemHandler.getStackInSlot(pIndex).isEmpty() && pAmount > 0 ? itemHandler.getStackInSlot(pIndex).split(pAmount) : ItemStack.EMPTY;
    }
    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        return takeItem(this.itemHandler, pSlot);
    }
    public static ItemStack takeItem(ItemStackHandler itemHandler, int pSlot) {
        return pSlot >= 0 && pSlot < itemHandler.getSlots() ? itemHandler.insertItem(pSlot, ItemStack.EMPTY, false) : ItemStack.EMPTY;
    }
    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        // FIX: Removed the forced item count truncation (Math.min(..., 1)).
        // Reason: With getMaxStackSize() and canPlaceItem() overridden, vanilla hoppers and compliant external logistics will naturally only insert 1 item at a time.
        // Truncating the stack size here would cause excess items to be permanently voided/deleted if a non-compliant external pipe forces a whole stack into the slot.
        this.itemHandler.setStackInSlot(pSlot, pStack);
        this.setChanged();
    }

    @Override
    public int getMaxStackSize() {
        // FIX: Overrode the getMaxStackSize method from the Container interface.
        // Reason: The vanilla Container defaults to a max stack size of 64. Without this override, vanilla hoppers and external logistics would assume the slot can hold a full stack, extracting multiple items at once.
        // Limiting this to 1 restricts external insertions at the source, ensuring only one item is transferred per operation.
        return 1;
    }

    @Override
    public boolean canPlaceItem(int pIndex, @NotNull ItemStack pStack) {
        // FIX: Overrode the canPlaceItem method from the Container interface.
        // Reason: Vanilla defaults to allowing item placement in any slot. This restricts insertion to empty slots only.
        // This prevents external pipes from forcing new inputs into a slot that is currently baking or already holds a finished product, avoiding item stacking or overwriting.
        return this.getItem(pIndex).isEmpty();
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            this.itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    @Override
    public Level level() {
        return this.level;
    }

    @Override
    public Vec3 position() {
        return null;
    }

    @Override
    public float getVisualRotationYInDegrees() {
        return 0;
    }

    public enum State {
        OPEN_PROCESS,
        OPEN,
        CLOSE_PROCESS,
        CLOSE,
    }
}
