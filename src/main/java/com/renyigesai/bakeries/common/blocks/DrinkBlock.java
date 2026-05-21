package com.renyigesai.bakeries.common.blocks;

import com.renyigesai.bakeries.api.blocks.AbstractPileBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DrinkBlock extends AbstractPileBlock {

    private static final VoxelShape BOX;
    private static final VoxelShape BOX_2;

    public static final IntegerProperty PILE = IntegerProperty.create("pile",1,2);
    public DrinkBlock(Identifier identifier) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(0.1F,0.1F).setId(ResourceKey.create(Registries.BLOCK,identifier)));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PILE,1));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int value = pState.getValue(PILE);
        return value == 1 ? BOX : BOX_2;
    }

    @Override
    public Property<Integer> getPileProperty() {
        return PILE;
    }

    @Override
    public int getMaxPile() {
        return 2;
    }

    @Override
    public SoundEvent getPlaceSound() {
        return SoundEvents.GLASS_PLACE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,PILE);
    }

    static {
        BOX = box(6.0, 0, 6.0, 10, 7.5, 10);
        BOX_2 = box(4.0, 0, 4.0, 12, 7.5, 12);
    }
}
