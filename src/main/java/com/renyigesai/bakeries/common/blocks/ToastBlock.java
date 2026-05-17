package com.renyigesai.bakeries.common.blocks;

import com.mojang.serialization.MapCodec;
import com.renyigesai.bakeries.api.blocks.AKnifeCutBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ToastBlock extends AKnifeCutBlock {
    /*由于吐司只是一个单纯的方块，他的物品不需要可食用*/
    public static final IntegerProperty PILE = IntegerProperty.create("pile",1,2);
    protected static final VoxelShape X_BOX = Block.box(6.0D, 0.0D, 4.0D, 10.0D, 5.0D, 12.0D);
    protected static final VoxelShape Z_BOX = Block.box(4.0D, 0.0D, 6.0D, 12.0D, 5.0D, 10.0D);
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);
    public final Supplier<Item> sliceItem;

    public ToastBlock(BlockBehaviour.Properties pProperties, Supplier<Item> sliceItem) {
        super(pProperties);
        this.sliceItem = sliceItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(SLICE,1).setValue(PILE,1).setValue(FACING,Direction.NORTH));
    }

    private ToastBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.sliceItem = ItemStack.EMPTY::getItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(SLICE,1).setValue(PILE,1).setValue(FACING,Direction.NORTH));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState pState, Level level, BlockPos pos, Player pPlayer, InteractionHand pHand, BlockHitResult hitResult) {
        ItemStack hand = pPlayer.getItemInHand(pHand);
        int pile = pState.getValue(PILE);
        if (isKnifeItem(hand) && pile == 1){
            cut(level,pState,pos,pPlayer,hand,pHand);
            return InteractionResult.SUCCESS;
        }
        int slice = pState.getValue(SLICE);
        if (hand.is(asItem()) && pile < 2 && slice == 1){
            if (!pPlayer.getAbilities().instabuild) {
                hand.shrink(1);
            }
            return pileUp(level,pos,pState);
        }
        return super.useItemOn(stack, pState, level, pos, pPlayer, pHand, hitResult);
    }

    @Override
    public Property<Integer> getSliceProperty() {
        return SLICE;
    }

    @Override
    public int getMaxSlice() {
        return 4;
    }

    @Override
    public int getSliceItemCount() {
        return 1;
    }

    public Item getSliceItem() {
        return this.sliceItem.get();
    }

    protected InteractionResult pileUp(Level level, BlockPos pos, BlockState state){
        level.setBlock(pos,state.setValue(PILE,state.getValue(PILE) + 1),3);
        level.playSound(null, pos, SoundEvents.WOOL_STEP, SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Direction direction = state.getValue(FACING);
        int pile = state.getValue(PILE);
        if (pile > 1){
            return SHAPE;
        }
        return direction.getAxis() == Direction.Axis.X ? X_BOX : Z_BOX;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SLICE,PILE,FACING);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction facing, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return facing == Direction.DOWN && !state.canSurvive(level, neighbourPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, facing, neighbourPos, neighbourState, random);
    }

    public static int getOutputSignal(int pEaten) {
        return pEaten * 2;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    protected MapCodec<ToastBlock> codec() {
        return simpleCodec(ToastBlock::new);
    }
}
