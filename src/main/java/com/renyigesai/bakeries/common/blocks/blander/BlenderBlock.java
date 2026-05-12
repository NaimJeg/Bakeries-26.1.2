package com.renyigesai.bakeries.common.blocks.blander;

import com.mojang.serialization.MapCodec;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.init.BakeriesSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlenderBlock extends BaseEntityBlock {
    public static final EnumProperty<@org.jetbrains.annotations.NotNull Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final VoxelShape X_BOX = box(4.0,0.0,0.0,12.0,16.0,16.0);
    public static final VoxelShape Z_BOX = box(0.0,0.0,4.0,16.0,16.0,12.0);
    public BlenderBlock(Identifier identifier) {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(3.5F, 3.5F).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).setId(ResourceKey.create(Registries.BLOCK,identifier)));
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED,false).setValue(FACING, Direction.NORTH));
    }

    public BlenderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BlenderBlock::new);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? Z_BOX : X_BOX;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? createTickerHelper(pBlockEntityType, BakeriesBlocks.Entities.BLENDER_ENTITY.get(),
                BlenderBlockEntity::clientTick) : createTickerHelper(pBlockEntityType, BakeriesBlocks.Entities.BLENDER_ENTITY.get(),
                BlenderBlockEntity::craftTick);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hitResult) {
        super.useWithoutItem(blockstate, world, pos, entity, hitResult);
        if(!world.isClientSide()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            super.useWithoutItem(blockstate, world, pos, entity, hitResult);
            if (blockEntity instanceof BlenderBlockEntity blenderBlockEntity) {
                entity.openMenu(blenderBlockEntity, pos);
                return InteractionResult.CONSUME;
            }else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(POWERED)){
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 0.5D;
            double d2 = (double)pPos.getZ() + 0.5D;
            pLevel.playLocalSound(d0, d1, d2, BakeriesSounds.BLENDER.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED,FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlenderBlockEntity(blockPos,blockState);
    }
}
