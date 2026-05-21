package com.renyigesai.bakeries.common.blocks.glass_drink_cup;

import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.drink.DrinkInput;
import com.renyigesai.bakeries.common.recipe.drink.DrinkRecipe;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import com.renyigesai.bakeries.common.utils.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("removal")
public class GlassDrinkCupBlockEntity extends BlockEntity {
    protected ItemStackHandler inventory = new ItemStackHandler(5);
    public int stage;

    private final RecipeManager.CachedCheck<DrinkInput, DrinkRecipe> CHECK = RecipeManager.createCheck(BakeriesRecipes.DRINK_TYPE.get());

    public GlassDrinkCupBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BakeriesBlocks.Entities.DRINK_CUP_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        this.inventory = new ItemStackHandler(5);
        WorldUtils.loadItemsToHandler(input,this.inventory,"Items");

        stage = input.getIntOr("Stage",-1);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        WorldUtils.saveAllItems(output, ItemUtils.toNonNullList(this.inventory),"Items");
        output.putInt("Stage",this.stage);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public boolean isEmpty(){
        for (int i = 0; i < inventory.getSlots()-1; i++) {
            if (inventory.getStackInSlot(i).isEmpty()){
                return true;
            }
        }
        return false;
    }

    public void addItem(ItemStack stack, Player player) {
        for (int i = 0; i < inventory.getSlots()-1; i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                if (stack.getCraftingRemainder() != null) {
                    inventory.setStackInSlot(i, stack);
                    ItemUtils.givePlayerItem(player, stack.getCraftingRemainder().create());
                    break;
                }
                inventory.setStackInSlot(i, stack);
                break;
            }
        }
        forcedRefresh();
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public void forcedRefresh(){
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            stacks.add(this.inventory.getStackInSlot(i));
        }
        Optional<RecipeHolder<DrinkRecipe>> recipeOptional = getMatchingRecipe(stacks);
        if (recipeOptional.isPresent()){
            DrinkRecipe coffeeRecipe = recipeOptional.get().value();
            inventory.setStackInSlot(4,coffeeRecipe.assemble(new DrinkInput(stacks)));
        }
    }

    public void removeItems(){
        for (int i = 0; i < inventory.getSlots(); i++) {
            inventory.setStackInSlot(i,ItemStack.EMPTY);
        }
    }

    private Optional<RecipeHolder<DrinkRecipe>> getMatchingRecipe(List<ItemStack> stacks) {
        if (level == null || !(level instanceof ServerLevel)) return Optional.empty();
        return CHECK.getRecipeFor(new DrinkInput(stacks), (ServerLevel) this.level);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GlassDrinkCupBlockEntity blockEntity) {
        if (!blockEntity.isEmpty()) {
            blockEntity.craftTick();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(pos, state, state, 2);
            }
        }
    }

    public void craftTick(){
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            stacks.add(this.inventory.getStackInSlot(i));
        }
        Optional<RecipeHolder<DrinkRecipe>> recipeOptional = getMatchingRecipe(stacks);
        if (recipeOptional.isPresent()) {
            DrinkRecipe recipe = recipeOptional.get().value();
            for (int i = 0; i < recipe.getInputItems().size(); i++) {
                if (recipe.getInputItems().get(i).test(inventory.getStackInSlot(i))) {
                    break;
                }
            }
            inventory.setStackInSlot(4,recipe.assemble(new DrinkInput(stacks)).copy());
            setChanged();
        }
    }
}
