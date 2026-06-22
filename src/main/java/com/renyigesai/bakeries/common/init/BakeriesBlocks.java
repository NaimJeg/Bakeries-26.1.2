package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.blocks.*;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlock;
import com.renyigesai.bakeries.common.blocks.blander.BlenderBlockEntity;
import com.renyigesai.bakeries.common.blocks.bread_basket.BreadBasketBlock;
import com.renyigesai.bakeries.common.blocks.bread_basket.BreadBasketBlockEntity;
import com.renyigesai.bakeries.common.blocks.bread_rack.BreadRackBlock;
import com.renyigesai.bakeries.common.blocks.bread_rack.BreadRackBlockEntity;
import com.renyigesai.bakeries.common.blocks.bread_rack.GlassBreadRackBlock;
import com.renyigesai.bakeries.common.blocks.cupboard.CupboardBlock;
import com.renyigesai.bakeries.common.blocks.cupboard.CupboardBlockEntity;
import com.renyigesai.bakeries.common.blocks.dough_crafting_table.DoughCraftingTableBlock;
import com.renyigesai.bakeries.common.blocks.dough_crafting_table.DoughCraftingTableBlockEntity;
import com.renyigesai.bakeries.common.blocks.fluid.SaltWaterFluidsBlock;
import com.renyigesai.bakeries.common.blocks.glass_drink_cup.GlassDrinkCupBlock;
import com.renyigesai.bakeries.common.blocks.glass_drink_cup.GlassDrinkCupBlockEntity;
import com.renyigesai.bakeries.common.blocks.letter_tile.LetterTileBlock;
import com.renyigesai.bakeries.common.blocks.letter_tile.LetterTileBlockEntity;
import com.renyigesai.bakeries.common.blocks.luminous_light_sign.LuminousLightSignBlock;
import com.renyigesai.bakeries.common.blocks.luminous_light_sign.LuminousLightSignBlockEntity;
import com.renyigesai.bakeries.common.blocks.menu.MenuBlock;
import com.renyigesai.bakeries.common.blocks.menu.MenuBlockEntity;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlock;
import com.renyigesai.bakeries.common.blocks.mix_block.MixBlockEntity;
import com.renyigesai.bakeries.common.blocks.moka_pot.MokaPotBlock;
import com.renyigesai.bakeries.common.blocks.moka_pot.MokaPotBlockEntity;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlock;
import com.renyigesai.bakeries.common.blocks.oven.OvenBlockEntity;
import com.renyigesai.bakeries.common.blocks.sofa.SofaBlock;
import com.renyigesai.bakeries.common.blocks.tank.CheeseTankBkock;
import com.renyigesai.bakeries.common.blocks.tank.FermentationTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.MilkTankBlock;
import com.renyigesai.bakeries.common.blocks.tank.YeastTankBlock;
import com.renyigesai.bakeries.common.blocks.toaster.ToasterBlock;
import com.renyigesai.bakeries.common.blocks.toaster.ToasterBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BakeriesBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BakeriesMod.MODID);

    public static final DeferredBlock<MixBlock> MIX_BLOCK;
    public static final DeferredBlock<Block> BLENDER;
    public static final DeferredBlock<Block> DOUGH_CRAFTING_TABLE;
    public static final DeferredBlock<Block> OVEN;
    public static final DeferredBlock<Block> CUPBOARD;
    public static final DeferredBlock<Block> BREAD_RACK;
    public static final DeferredBlock<Block> GLASS_BREAD_RACK;
    public static final DeferredBlock<Block> MOKA_POT;
    public static final DeferredBlock<Block> DRINK_CUP;
    public static final DeferredBlock<Block> TOASTER;
    public static final DeferredBlock<Block> LUMINOUS_LIGHT_SIGN;
    public static final DeferredBlock<Block> BREAD_BASKET;
    public static final DeferredBlock<Block> MENU;
    public static final DeferredBlock<Block> LETTER_TILE;


    /**?????*/
    public static final DeferredBlock<Block> FERMENTATION_TANK;
    public static final DeferredBlock<Block> YEAST_TANK;
    public static final DeferredBlock<Block> MILK_TANK;
    public static final DeferredBlock<Block> CHEESE_TANK;

    public static final DeferredBlock<Block> MOULD;

    public static final DeferredBlock<Block> OLIVE_OIL;
    public static final DeferredBlock<Block> BEARNAISE;

    public static final DeferredBlock<Block> COFFEE_TABLE;

    public static final DeferredBlock<SofaBlock> SOFA_WHITE;
    public static final DeferredBlock<SofaBlock> SOFA_RED;
    public static final DeferredBlock<SofaBlock> SOFA_LIGHT_GRAY;

    public static final DeferredBlock<Block> WOOD_COUNTER;

    public static final DeferredBlock<Block> CASH_REGISTER_COMPUTER;

    public static final DeferredBlock<Block> WHOLE_WHEAT_FLOUR_BAG;
    public static final DeferredBlock<Block> FLOUR_BAG;

    public static final DeferredBlock<Block> BLACK_WHITE_CONCRETE;


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
    public static final DeferredBlock<Block> COUNTRY_BREAD;
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

    /**饮料方块*/
    public static final DeferredBlock<Block> ICED_AMERICAN;
    public static final DeferredBlock<Block> ICED_LATTE;
    public static final DeferredBlock<Block> BROWN_SUGAR_LATTE;
    public static final DeferredBlock<Block> CREAM_BINGLE_COFFEE;
    public static final DeferredBlock<Block> MATCHA_LATTE;
    public static final DeferredBlock<Block> MATCHA_PARFAIT;
    public static final DeferredBlock<Block> TARO_MILK;

    public static final DeferredBlock<Block> TOMATO;
    public static final DeferredBlock<Block> TARO;
    public static final DeferredBlock<Block> COFFEE_PLANT;

    /**盐相关*/
    public static final DeferredBlock<LiquidBlock> SALT_WATER_BLOCK;
    public static final DeferredBlock<Block> RAW_SALT_BLOCK;


    static {

        FERMENTATION_TANK = BLOCKS.register("fermentation_tank",FermentationTankBlock::new);
        YEAST_TANK = BLOCKS.register("yeast_tank",YeastTankBlock::new);
        MILK_TANK = BLOCKS.register("milk_tank",MilkTankBlock::new);
        CHEESE_TANK = BLOCKS.register("cheese_tank",CheeseTankBkock::new);
        MOULD = BLOCKS.register("mould",()-> new MouldBlock(BakeriesItems.MOULD,"mould"));
        OLIVE_OIL = BLOCKS.register("olive_oil",TanPieBlock::new);
        BEARNAISE = BLOCKS.register("bearnaise",TanPieBlock::new);

        /*沙发*/

        COFFEE_TABLE = BLOCKS.register("coffee_table",CoffeeTableBlock::new);

        SOFA_WHITE = BLOCKS.register("sofa_white",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_white")), SofaBlock.Color.WHITE));
        SOFA_RED = BLOCKS.register("sofa_red",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_red")), SofaBlock.Color.RED));
        SOFA_LIGHT_GRAY = BLOCKS.register("sofa_light_gray",()-> new SofaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("sofa_light_gray")), SofaBlock.Color.LIGHT_GRAY));

        WOOD_COUNTER = BLOCKS.register("wood_counter",WoodCounterBlock::new);

        CASH_REGISTER_COMPUTER = BLOCKS.register("cash_register_computer",CashRegisterComputerBlock::new);

        WHOLE_WHEAT_FLOUR_BAG = BLOCKS.register("whole_wheat_flour_bag",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).setId(modBlockId("whole_wheat_flour_bag"))));
        FLOUR_BAG = BLOCKS.register("flour_bag",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).setId(modBlockId("flour_bag"))));
        BLACK_WHITE_CONCRETE = BLOCKS.register("black_white_concrete", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).setId(modBlockId("black_white_concrete"))));

        MIX_BLOCK = BLOCKS.register("mix_block", MixBlock::new);
        BLENDER = BLOCKS.register("blender", BlenderBlock::new);
        DOUGH_CRAFTING_TABLE = BLOCKS.register("dough_crafting_table", DoughCraftingTableBlock::new);
        OVEN = BLOCKS.register("oven", OvenBlock::new);
        CUPBOARD = BLOCKS.register("cupboard",()-> new CupboardBlock(cupboardProperties("cupboard")));
        BREAD_RACK = BLOCKS.register("bread_rack",()-> new BreadRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(modBlockId("bread_rack"))));
        GLASS_BREAD_RACK = BLOCKS.register("glass_bread_rack",()-> new GlassBreadRackBlock(BlockBehaviour.Properties.ofFullCopy(BREAD_RACK.get()).setId(modBlockId("glass_bread_rack"))));
        MOKA_POT = BLOCKS.register("moka_pot", MokaPotBlock::new);
        DRINK_CUP = BLOCKS.register("drink_cup", GlassDrinkCupBlock::new);
        TOASTER = BLOCKS.register("toaster", ToasterBlock::new);
        LUMINOUS_LIGHT_SIGN = BLOCKS.register("luminous_light_sign", LuminousLightSignBlock::new);
        BREAD_BASKET = BLOCKS.register("bread_basket", BreadBasketBlock::new);
        MENU = BLOCKS.register("menu", MenuBlock::new);
        LETTER_TILE = BLOCKS.register("letter_tile", LetterTileBlock::new);

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
        COUNTRY_BREAD = BLOCKS.register("country_bread",CountryBreadBlock::new);
        FLAT_CROISSANT = BLOCKS.register("flat_croissant", BreadBlock::new);
        TOAST = register("toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F).setId(modBlockId("toast")), BakeriesItems.SLICED_TOAST));
        MOULD_TOAST = BLOCKS.register("mould_toast", () ->
                new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F).setId(modBlockId("mould_toast")),BakeriesItems.TOAST));
        CHEESE_COCOA_TOAST = register("cheese_cocoa_toast", () -> new ToastBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.5F,0.5F).setId(modBlockId("cheese_cocoa_toast")), BakeriesItems.SLICED_CHEESE_COCOA_TOAST));
        MOULD_CHEESE_COCOA_TOAST = BLOCKS.register("mould_cheese_cocoa_toast", () ->
                new MouldToastBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5F,0.5F).setId(modBlockId("mould_cheese_cocoa_toast")),BakeriesItems.CHEESE_COCOA_TOAST));
        EGG_TART = BLOCKS.register("egg_tart",BreadBlock::new);
        TARO_SALT_YOLK_BREAD = BLOCKS.register("taro_salt_yolk_bread",BreadBlock::new);

        /**饮料方块*/
        ICED_AMERICAN = drinkBlock("iced_american");
        ICED_LATTE = drinkBlock("iced_latte");
        BROWN_SUGAR_LATTE = drinkBlock("brown_sugar_latte");
        CREAM_BINGLE_COFFEE = drinkBlock("cream_bingle_coffee");
        MATCHA_LATTE = drinkBlock("matcha_latte");
        MATCHA_PARFAIT = drinkBlock("matcha_parfait");
        TARO_MILK = drinkBlock("taro_milk");

        TOMATO = BLOCKS.register("tomato", TomatoBlock::new);
        TARO = BLOCKS.register("taro", TaroBlock::new);
        COFFEE_PLANT = BLOCKS.register("coffee_plant",CoffeePlantBlock::new);

        SALT_WATER_BLOCK = BLOCKS.register("salt_water_block", SaltWaterFluidsBlock::new);
        RAW_SALT_BLOCK = BLOCKS.register("raw_salt_block",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(modBlockId("raw_salt_block"))));
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

    private static DeferredBlock<Block> drinkBlock(String name){
        return BLOCKS.register(name,DrinkBlock::new);
    }

    public static class Entities {
        public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BakeriesMod.MODID);
        public static final Supplier<BlockEntityType<MixBlockEntity>> MIX_BLOCK_ENTITY = REGISTER.register("mix_block", () -> new BlockEntityType<>(MixBlockEntity::new, MIX_BLOCK.get()));
        public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER_ENTITY = REGISTER.register("blender", () -> new BlockEntityType<>(BlenderBlockEntity::new, BLENDER.get()));
        public static final Supplier<BlockEntityType<DoughCraftingTableBlockEntity>> DOUGH_CRAFTING_TABLE_ENTITY = REGISTER.register("dough_crafting_table", () -> new BlockEntityType<>(DoughCraftingTableBlockEntity::new, DOUGH_CRAFTING_TABLE.get()));
        public static final Supplier<BlockEntityType<OvenBlockEntity>> OVEN_ENTITY = REGISTER.register("oven", () -> new BlockEntityType<>(OvenBlockEntity::new, OVEN.get()));
        public static final Supplier<BlockEntityType<CupboardBlockEntity>> CUPBOARD_ENTITY = REGISTER.register("cupboard", () -> new BlockEntityType<>(CupboardBlockEntity::new, CUPBOARD.get()));
        public static final Supplier<BlockEntityType<BreadRackBlockEntity>> BREAD_RACK_ENTITY = REGISTER.register("bread_rack", () -> new BlockEntityType<>(BreadRackBlockEntity::new, BREAD_RACK.get(),GLASS_BREAD_RACK.get()));
        public static final Supplier<BlockEntityType<MokaPotBlockEntity>> MOKA_POT_ENTITY = REGISTER.register("moka_pot", () -> new BlockEntityType<>(MokaPotBlockEntity::new, MOKA_POT.get()));
        public static final Supplier<BlockEntityType<GlassDrinkCupBlockEntity>> DRINK_CUP_ENTITY = REGISTER.register("drink_cup", () -> new BlockEntityType<>(GlassDrinkCupBlockEntity::new, DRINK_CUP.get()));
        public static final Supplier<BlockEntityType<ToasterBlockEntity>> TOASTER_ENTITY = REGISTER.register("toaster", () -> new BlockEntityType<>(ToasterBlockEntity::new, TOASTER.get()));
        public static final Supplier<BlockEntityType<LuminousLightSignBlockEntity>> LUMINOUS_LIGHT_SIGN_ENTITY = REGISTER.register("luminous_light_sign", () -> new BlockEntityType<>(LuminousLightSignBlockEntity::new, LUMINOUS_LIGHT_SIGN.get()));
        public static final Supplier<BlockEntityType<BreadBasketBlockEntity>> BREAD_BASKET_ENTITY = REGISTER.register("bread_basket", () -> new BlockEntityType<>(BreadBasketBlockEntity::new, BREAD_BASKET.get()));
        public static final Supplier<BlockEntityType<MenuBlockEntity>> MENU_ENTITY = REGISTER.register("menu", () -> new BlockEntityType<>(MenuBlockEntity::new, MENU.get()));
        public static final Supplier<BlockEntityType<LetterTileBlockEntity>> LETTER_TILE_ENTITY = REGISTER.register("letter_tile", () -> new BlockEntityType<>(LetterTileBlockEntity::new, LETTER_TILE.get()));
    }


}
