package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.blocks.BreadBlock;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlock;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlockEntity;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlock;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlockEntity;
import com.renyigesai.bakeries.common.blocks.tank.CheeseTankBkock;
import com.renyigesai.bakeries.common.blocks.tank.FermentationTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.MilkTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.YeastTankBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BakeriesBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BakeriesMod.MODID);

    public static final DeferredBlock<Block> WOOD_TRAY;
    public static final DeferredBlock<@org.jetbrains.annotations.NotNull MixBlock> MIX_BLOCK;
    public static final DeferredBlock<Block> BLENDER;


    /**发酵罐*/
    public static final DeferredBlock<Block> FERMENTATION_TANK;
    public static final DeferredBlock<Block> YEAST_TANK;
    public static final DeferredBlock<Block> MILK_TANK;
    public static final DeferredBlock<Block> CHEESE_TANK;


    /*面包方块*/

    /**贝果*/
    public static final DeferredBlock<Block> BAGEL;
    /**全麦贝果*/
    public static final DeferredBlock<Block> WHOLE_WHEAT_BAGEL;
    /**圆面包*/
    public static final DeferredBlock<Block> ROUND_BREAD;
    /**莓果面包*/
    public static final DeferredBlock<Block> BERRY_BREAD;
    /**乳酪面包*/
    public static final DeferredBlock<Block> CHEESE_CREAM_BREAD;
    /**红糖卷*/
    public static final DeferredBlock<Block> BROWN_SUGAR_ROLL;
    /**菠萝包*/
    public static final DeferredBlock<Block> PINEAPPLE_BUN;
    /**菠萝包*/
    public static final DeferredBlock<Block> PINEAPPLE_OIL;
    /**肉松面包卷*/
    public static final DeferredBlock<Block> MEAT_FLOSS_BREAD_ROLL;
    /**可颂*/
    public static final DeferredBlock<Block> CROISSANT;
    /**脏脏包*/
    public static final DeferredBlock<Block> DIRTY_CHOCO_CROISSANT;
    /**盐可颂*/
    public static final DeferredBlock<Block> SALT_CROISSANT;
    /**恰巴塔面包*/
    public static final DeferredBlock<Block> CIABATTA;
    /**佛卡夏面包*/
    public static final DeferredBlock<Block> FOCACCIA;
    /**浆果贝果*/
    public static final DeferredBlock<Block> BERRY_BAGEL;
    /**填酱贝果*/
    public static final DeferredBlock<Block> BAGEL_FILLED_SAUCE;
    /**填馅法棍*/
    public static final DeferredBlock<Block> BAGUETTE_WITH_FILLING;
    /**番茄奶酪可颂三明治*/
    public static final DeferredBlock<Block> TOMATO_CHEESE_CROISSANT_SANDWICH;
    /**法棍*/
    public static final DeferredBlock<Block> BAGUETTE;
    /**乡村面包*/
//    public static final DeferredBlock<Block> COUNTRY_BREAD;
    /**扁可颂*/
    public static final DeferredBlock<Block> FLAT_CROISSANT;
    /**吐司*/
//    public static final DeferredBlock<Block> TOAST;
//    public static final DeferredBlock<Block> MOULD_TOAST;
//    public static final DeferredBlock<Block> CHEESE_COCOA_TOAST;
//    public static final DeferredBlock<Block> MOULD_CHEESE_COCOA_TOAST;

    /**蛋挞*/
    public static final DeferredBlock<Block> EGG_TART;
    /**芋泥咸蛋黄面包*/
    public static final DeferredBlock<Block> TARO_SALT_YOLK_BREAD;


    static {

        FERMENTATION_TANK = BLOCKS.register("fermentation_tank",FermentationTankBlock::new);
        YEAST_TANK = BLOCKS.register("yeast_tank",YeastTankBlock::new);
        MILK_TANK = BLOCKS.register("milk_tank",MilkTankBlock::new);
        CHEESE_TANK = BLOCKS.register("cheese_tank",CheeseTankBkock::new);

        WOOD_TRAY = BLOCKS.register("wood_tray",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("bakeries","wood_tray")))));
        MIX_BLOCK = BLOCKS.register("mix_block", MixBlock::new);
        BLENDER = BLOCKS.register("blender", BlenderBlock::new);

        /*面包方块*/
        BAGEL = BLOCKS.register("bagel", BreadBlock::new);
        WHOLE_WHEAT_BAGEL = BLOCKS.register("whole_wheat_bagel", BreadBlock::new);
        ROUND_BREAD = BLOCKS.register("round_bread", BreadBlock::new);
        BERRY_BREAD = BLOCKS.register("berry_bread", BreadBlock::new);
        CHEESE_CREAM_BREAD = BLOCKS.register("cheese_cream_bread", BreadBlock::new);
        BROWN_SUGAR_ROLL = BLOCKS.register("brown_sugar_roll", BreadBlock::new);
        PINEAPPLE_BUN = BLOCKS.register("pineapple_bun", BreadBlock::new);
        PINEAPPLE_OIL = BLOCKS.register("pineapple_oil", BreadBlock::new);
        MEAT_FLOSS_BREAD_ROLL = BLOCKS.register("meat_floss_bread_roll", BreadBlock::new);
        CROISSANT = BLOCKS.register("croissant", BreadBlock::new);
        DIRTY_CHOCO_CROISSANT = BLOCKS.register("dirty_choco_croissant", BreadBlock::new);
        SALT_CROISSANT = BLOCKS.register("salt_croissant", BreadBlock::new);
        CIABATTA = BLOCKS.register("ciabatta", BreadBlock::new);
        FOCACCIA = BLOCKS.register("focaccia",BreadBlock::new);
        BERRY_BAGEL = BLOCKS.register("berry_bagel",BreadBlock::new);
        BAGEL_FILLED_SAUCE = BLOCKS.register("bagel_filled_sauce",BreadBlock::new);
        BAGUETTE_WITH_FILLING = BLOCKS.register("baguette_with_filling",BreadBlock::new);
        TOMATO_CHEESE_CROISSANT_SANDWICH = BLOCKS.register("tomato_cheese_croissant_sandwich",BreadBlock::new);
        BAGUETTE = BLOCKS.register("baguette", BreadBlock::new);
//        COUNTRY_BREAD = register("country_bread",()-> new CountryBreadBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F)));
        FLAT_CROISSANT = BLOCKS.register("flat_croissant", BreadBlock::new);
//        TOAST = register("toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F), BakeriesItems.SLICED_TOAST));
//        MOULD_TOAST = REGISTER.register("mould_toast", () -> new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F),BakeriesItems.TOAST));
//        CHEESE_COCOA_TOAST = register("cheese_cocoa_toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F), BakeriesItems.SLICED_CHEESE_COCOA_TOAST));
//        MOULD_CHEESE_COCOA_TOAST = REGISTER.register("mould_cheese_cocoa_toast", () -> new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F),BakeriesItems.CHEESE_COCOA_TOAST));
        EGG_TART = BLOCKS.register("egg_tart",BreadBlock::new);
        TARO_SALT_YOLK_BREAD = BLOCKS.register("taro_salt_yolk_bread",BreadBlock::new);
    }

    private static<B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
        return BLOCKS.register(name, block);
    }

    public static class Entities {
        public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BakeriesMod.MODID);
        public static final Supplier<BlockEntityType<MixBlockEntity>> MIX_BLOCK_ENTITY = REGISTER.register("mix_block", () -> new BlockEntityType<>(MixBlockEntity::new, MIX_BLOCK.get()));
        public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER_ENTITY = REGISTER.register("blender", () -> new BlockEntityType<>(BlenderBlockEntity::new, BLENDER.get()));
    }


}
