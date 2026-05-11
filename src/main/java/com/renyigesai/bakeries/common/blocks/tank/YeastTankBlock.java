package com.renyigesai.bakeries.common.blocks.tank;

import com.renyigesai.bakeries.common.blocks.TankBlock;
import com.renyigesai.bakeries.common.init.BakeriesBlocks;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class YeastTankBlock extends TankBlock {

    public static final IntegerProperty YEAST = IntegerProperty.create("yeast", 0, 3);

    public YeastTankBlock(Identifier identifier) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(ResourceKey.create(Registries.BLOCK,identifier)));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(YEAST, 3));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.is(Items.GLASS_BOTTLE)){
            return ladleOut(level,pos,state,player,hand);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public static InteractionResult ladleOut(Level level, BlockPos pos, BlockState state, Player playerIn, InteractionHand pHand){
        ItemStack hand = playerIn.getItemInHand(pHand);
        int yeast = state.getValue(YEAST);
        if (yeast > 1){
            level.setBlock(pos,state.setValue(YEAST,yeast -1),3);
        }else {
            level.setBlock(pos, BakeriesBlocks.FERMENTATION_TANK.get().defaultBlockState(),0);
        }
        hand.shrink(1);
        ItemUtils.givePlayerItem(playerIn,new ItemStack(BakeriesItems.BOTTLE_YEAST.get()));
        level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(YEAST);
    }
}
