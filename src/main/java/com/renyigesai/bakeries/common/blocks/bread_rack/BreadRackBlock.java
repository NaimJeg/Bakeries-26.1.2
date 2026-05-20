package com.renyigesai.bakeries.common.blocks.bread_rack;

import com.renyigesai.bakeries.api.ItemStackHandler;
import com.renyigesai.bakeries.common.blocks.HorizontalConnectBlock;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BreadRackBlock extends HorizontalConnectBlock implements EntityBlock {

    private static final VoxelShape BOX = box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public BreadRackBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return BOX;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()){
            return InteractionResult.SUCCESS;
        }
        ItemStack itemInHand = player.getItemInHand(hand);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BreadRackBlockEntity rackBlock){
            if (player.isShiftKeyDown()){
                return take(rackBlock,state,level,pos,player);
            }else {
                return put(rackBlock,state,level,pos,itemInHand);
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public InteractionResult put(BreadRackBlockEntity rackBlock,BlockState pState, Level pLevel, BlockPos pPos, ItemStack itemInHand){
        ItemStackHandler items = rackBlock.getInventory();
        boolean flag = false;
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack item = items.getStackInSlot(i);
            if (item.isEmpty()){
                ItemStack copy = itemInHand.copy();
                itemInHand.shrink(1);
                copy.setCount(1);
                flag = rackBlock.setItem(i,copy);
                break;
            }
        }
        if (flag){
            pLevel.playSound(null,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public InteractionResult take(BreadRackBlockEntity rackBlock,BlockState pState, Level pLevel, BlockPos pPos,Player player){
        if (rackBlock.isEmpty()){
            return InteractionResult.FAIL;
        }
        int itemsCount = rackBlock.getItemsCount();
        ItemUtils.givePlayerItem(player,rackBlock.getItem(itemsCount-1).copy());
        rackBlock.setItem(itemsCount-1,ItemStack.EMPTY);
        pLevel.playSound(null,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BreadRackBlockEntity(blockPos,blockState);
    }
}
