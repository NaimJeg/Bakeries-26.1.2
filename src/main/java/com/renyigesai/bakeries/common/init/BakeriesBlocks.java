package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.blocks.*;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlock;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlockEntity;
import com.renyigesai.bakeries.common.blocks.cupboard.CupboardBlock;
import com.renyigesai.bakeries.common.blocks.cupboard.CupboardBlockEntity;
import com.renyigesai.bakeries.common.blocks.dough_crafting_table.DoughCraftingTableBlock;
import com.renyigesai.bakeries.common.blocks.dough_crafting_table.DoughCraftingTableBlockEntity;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlock;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlockEntity;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlock;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlockEntity;
import com.renyigesai.bakeries.common.blocks.sofa.SofaBlock;
import com.renyigesai.bakeries.common.blocks.tank.CheeseTankBkock;
import com.renyigesai.bakeries.common.blocks.tank.FermentationTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.MilkTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.YeastTankBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BakeriesBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BakeriesMod.MODID);

    public static final DeferredBlock<Block> WOOD_TRAY;
    public static final DeferredBlock<@org.jetbrains.annotations.NotNull MixBlock> MIX_BLOCK;
    public static final DeferredBlock<Block> BLENDER;
    public static final DeferredBlock<Block> DOUGH_CRAFTING_TABLE;
    public static final DeferredBlock<Block> OVEN;
    public static final DeferredBlock<Block> CUPBOARD;


    /**?????*/
    public static final DeferredBlock<Block> FERMENTATION_TANK;
    public static final DeferredBlock<Block> YEAST_TANK;
    public static final DeferredBlock<Block> MILK_TANK;
    public static final DeferredBlock<Block> CHEESE_TANK;

    public static final DeferredBlock<Block> MOULD;

    public static final DeferredBlock<Block> OLIVE_OIL;
    public static final DeferredBlock<Block> BEARNAISE;

    public static final DeferredBlock<SofaBlock> SOFA_WHITE;
    public static final DeferredBlock<SofaBlock> SOFA_RED;
    public static final DeferredBlock<SofaBlock> SOFA_LIGHT_GRAY;


    /*???????*/

    /**????*/
    public static final DeferredBlock<Block> BAGEL;
    /**????*/
    public static final DeferredBlock<Block> WHOLE_WHEAT_BAGEL;
    /**????*/
    public static final DeferredBlock<Block> ROUND_BREAD;
    /**??????*/
    public static final DeferredBlock<Block> BERRY_BREAD;
    /**???????*/
    public static final DeferredBlock<Block> CHEESE_CREAM_BREAD;
    /**?????*/
    public static final DeferredBlock<Block> BROWN_SUGAR_ROLL;
    /**?????*/
    public static final DeferredBlock<Block> PINEAPPLE_BUN;
    /**?????*/
    public static final DeferredBlock<Block> PINEAPPLE_OIL;
    /**?????????*/
    public static final DeferredBlock<Block> MEAT_FLOSS_BREAD_ROLL;
    /**????*/
    public static final DeferredBlock<Block> CROISSANT;
    /**?????*/
    public static final DeferredBlock<Block> DIRTY_CHOCO_CROISSANT;
    /**?ο???*/
    public static final DeferredBlock<Block> SALT_CROISSANT;
    /**????????*/
    public static final DeferredBlock<Block> CIABATTA;
    /**???????*/
    public static final DeferredBlock<Block> FOCACCIA;
    /**????????*/
    public static final DeferredBlock<Block> BERRY_BAGEL;
    /**??????*/
    public static final DeferredBlock<Block> BAGEL_FILLED_SAUCE;
    /**???????*/
    public static final DeferredBlock<Block> BAGUETTE_WITH_FILLING;
    /**?????????????????*/
    public static final DeferredBlock<Block> TOMATO_CHEESE_CROISSANT_SANDWICH;
    /**????*/
    public static final DeferredBlock<Block> BAGUETTE;
    /**??????*/
//    public static final DeferredBlock<Block> COUNTRY_BREAD;
    /**?????*/
    public static final DeferredBlock<Block> FLAT_CROISSANT;
    /**???*/
    public static final DeferredBlock<Block> TOAST;
    public static final DeferredBlock<Block> MOULD_TOAST;
    public static final DeferredBlock<Block> CHEESE_COCOA_TOAST;
    public static final DeferredBlock<Block> MOULD_CHEESE_COCOA_TOAST;

    /**???*/
    public static final DeferredBlock<Block> EGG_TART;
    /**????????????*/
    public static final DeferredBlock<Block> TARO_SALT_YOLK_BREAD;

    public static final DeferredBlock<Block> TOMATO;


    static {

        FERMENTATION_TANK = BLOCKS.register("fermentation_tank",FermentationTankBlock::new);
        YEAST_TANK = BLOCKS.register("yeast_tank",YeastTankBlock::new);
        MILK_TANK = BLOCKS.register("milk_tank",MilkTankBlock::new);
        CHEESE_TANK = BLOCKS.register("cheese_tank",CheeseTankBkock::new);
        MOULD = BLOCKS.register("mould",()-> new MouldBlock(BakeriesItems.MOULD,"mould"));
        OLIVE_OIL = BLOCKS.register("olive_oil",TanPieBlock::new);
        BEARNAISE = BLOCKS.register("bearnaise",TanPieBlock::new);

        /*沙发*/
        SOFA_WHITE = BLOCKS.register("sofa_white",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_white")), SofaBlock.Color.WHITE));
        SOFA_RED = BLOCKS.register("sofa_red",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_red")), SofaBlock.Color.RED));
        SOFA_LIGHT_GRAY = BLOCKS.register("sofa_light_gray",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_light_gray")), SofaBlock.Color.LIGHT_GRAY));

        WOOD_TRAY = BLOCKS.register("wood_tray",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("bakeries","wood_tray")))));
        MIX_BLOCK = BLOCKS.register("mix_block", MixBlock::new);
        BLENDER = BLOCKS.register("blender", BlenderBlock::new);
        DOUGH_CRAFTING_TABLE = BLOCKS.register("dough_crafting_table", DoughCraftingTableBlock::new);
        OVEN = BLOCKS.register("oven", OvenBlock::new);
        CUPBOARD = BLOCKS.register("cupboard",()-> new CupboardBlock(cupboardProperties("cupboard")));

        /*???????*/
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
        TOAST = register("toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F).setId(modBlockId("toast")), BakeriesItems.SLICED_TOAST));
        MOULD_TOAST = BLOCKS.register("mould_toast", () ->
                new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F).setId(modBlockId("mould_toast")),BakeriesItems.TOAST));
        CHEESE_COCOA_TOAST = register("cheese_cocoa_toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F).setId(modBlockId("cheese_cocoa_toast")), BakeriesItems.SLICED_CHEESE_COCOA_TOAST));
        MOULD_CHEESE_COCOA_TOAST = BLOCKS.register("mould_cheese_cocoa_toast", () ->
                new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F).setId(modBlockId("mould_cheese_cocoa_toast")),BakeriesItems.CHEESE_COCOA_TOAST));
        EGG_TART = BLOCKS.register("egg_tart",BreadBlock::new);
        TARO_SALT_YOLK_BREAD = BLOCKS.register("taro_salt_yolk_bread",BreadBlock::new);

        TOMATO = BLOCKS.register("tomato", TomatoBlock::new);
    }

    private static<B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
        return BLOCKS.register(name, block);
    }

    public static ResourceKey<Block> modBlockId(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name));
    }

    private static BlockBehaviour.Properties cupboardProperties(String name){
        return BlockBehaviour.Properties.of().strength(2.0F,3.0F).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY).sound(SoundType.CHISELED_BOOKSHELF).setId(modBlockId(name));
    }

    public static class Entities {
        public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BakeriesMod.MODID);
        public static final Supplier<BlockEntityType<MixBlockEntity>> MIX_BLOCK_ENTITY = REGISTER.register("mix_block", () -> new BlockEntityType<>(MixBlockEntity::new, MIX_BLOCK.get()));
        public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER_ENTITY = REGISTER.register("blender", () -> new BlockEntityType<>(BlenderBlockEntity::new, BLENDER.get()));
        public static final Supplier<BlockEntityType<DoughCraftingTableBlockEntity>> DOUGH_CRAFTING_TABLE_ENTITY = REGISTER.register("dough_crafting_table", () -> new BlockEntityType<>(DoughCraftingTableBlockEntity::new, DOUGH_CRAFTING_TABLE.get()));
        public static final Supplier<BlockEntityType<OvenBlockEntity>> OVEN_ENTITY = REGISTER.register("oven", () -> new BlockEntityType<>(OvenBlockEntity::new, OVEN.get()));
        public static final Supplier<BlockEntityType<CupboardBlockEntity>> CUPBOARD_ENTITY = REGISTER.register("cupboard", () -> new BlockEntityType<>(CupboardBlockEntity::new, CUPBOARD.get()));
    }


}
